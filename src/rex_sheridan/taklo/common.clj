(ns rex-sheridan.taklo.common
  "Common functionality and initialization entry point"
    (:require [clojure.string :refer [join]]))

(defn create-authorization 
  "Returns a header map used for authorization as described by
   https://developer.atlassian.com/cloud/trello/guides/rest-api/authorization/#passing-token-and-key-in-api-requests"
  [api-key api-token]
  {"Authorization" (str "OAuth oauth_consumer_key=\"" api-key "\", oauth_token=\"" api-token "\"")})

(def default-endpoint-url "https://api.trello.com/1/")

(declare ^:dynamic *endpoint*)
(declare ^:dynamic *http-request-handler*)
(declare ^:dynamic *http-response-handler*)
(declare ^:dynamic *json-write*)

(def ^:dynamic *standard-request-options*
  {:accept :json
   :debug false
   :debug-body false})

(declare ^:dynamic *standard-request*)

;; Allows for two modes:
;; 1. static single initialization for the global level
;; 2. dynamic rebinding or higher order functions 
;;    https://stackoverflow.com/questions/11730828/clojure-and-dynamic
(defn init! [{:keys [http-request-fn response-handler-fn
                     json-write-fn endpoint-url api-key api-token
                     debug]
              :or {endpoint-url default-endpoint-url
                   http-request-fn identity
                   response-handler-fn identity
                   json-write-fn identity
                   debug false}}]
  (alter-var-root #'*endpoint* (constantly endpoint-url))
  (alter-var-root #'*http-request-handler* (constantly http-request-fn))
  (alter-var-root #'*http-response-handler* (constantly response-handler-fn))
  (alter-var-root #'*json-write* (constantly json-write-fn))
  
  (alter-var-root #'*standard-request-options*
                  (constantly
                   {:accept :json
                    :debug debug
                    :debug-body debug}))
  (alter-var-root #'*standard-request*
                  (constantly
                   (fn []
                     (merge *standard-request-options*
                            {:headers (create-authorization api-key api-token)}))))
  :initialized)

(defn with-path-prefix [prefix id & segments]
  (->> segments
       (concat [prefix id])
       (map name)
       (join "/")
       (apply str)))

(defn endpoint-url [path] (str *endpoint* path))

(defn- initialized? []
  (bound? #'*endpoint* 
          #'*http-request-handler* 
          #'*http-response-handler* 
          #'*json-write*
          #'*standard-request*))

(defn request
  ([req] (-> req *http-request-handler* *http-response-handler*))
  ([method path] (request method path {} {}))
  ([method path params] (request method path {} params))
  ([method path body params] (request method path body params {}))
  ([method path body params request-base]
   {:pre [(initialized?)]}
   (request (merge-with into
                        request-base
                        (*standard-request*)
                        {:method method
                         :url (endpoint-url path)}
                        {:query-params params}
                        (if (seq body)
                          {:content-type :json
                           :body (*json-write* body)}
                          {})))))