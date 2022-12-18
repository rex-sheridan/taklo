(ns rex-sheridan.taklo.common
  "Common functionality and initialization entry point"
    (:require [clojure.string :refer [join]]))

(defn create-authorization 
  "Returns a header map used for authorization as described by
   https://developer.atlassian.com/cloud/trello/guides/rest-api/authorization/#passing-token-and-key-in-api-requests"
  [api-key api-token]
  {"Authorization" (str "OAuth oauth_consumer_key=\"" api-key "\", oauth_token=\"" api-token "\"")})

(def default-endpoint-url "https://api.trello.com/1/")

;; Allows for two modes:
;; 1. static single initialization for the global level
;; 2. dynamic rebinding or higher order functions 
;;    https://stackoverflow.com/questions/11730828/clojure-and-dynamic
#_{:clj-kondo/ignore [:inline-def]}
(defn init! [{:keys [http-request-fn response-handler-fn
                     json-write-fn endpoint-url api-key api-token
                     debug]
              :or {endpoint-url default-endpoint-url
                   http-request-fn identity
                   response-handler-fn identity
                   json-write-fn identity
                   debug false}}]
  (def ^:dynamic *endpoint* endpoint-url)
  (def ^:dynamic *http-request-handler* http-request-fn)
  (def ^:dynamic *http-response-handler* response-handler-fn)
  (def ^:dynamic *json-write* json-write-fn)

  (def ^:dynamic *standard-request* {:headers (create-authorization api-key api-token)
                         :accept :json
                         :debug debug
                         :debug-body true}))

(defn with-path-prefix [prefix id & segments]
  (->> segments
       (concat [prefix id])
       (map name)
       (join "/")
       (apply str)))

(defn endpoint-url [path] (str *endpoint* path))

(defn request
  ([req] (-> req *http-request-handler* *http-response-handler*))
  ([method path] (request method path {} {}))
  ([method path params] (request method path {} params))
  ([method path body params] (request method path body params {}))
  ([method path body params request-base]
   (request (merge-with into
                        request-base
                        *standard-request*
                        {:method method
                         :url (endpoint-url path)}
                        {:query-params params}
                        (if (seq body)
                          {:content-type :json
                           :body (*json-write* body)}
                          {})))))