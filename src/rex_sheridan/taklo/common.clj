(ns rex-sheridan.taklo.common
    (:require [clojure.string :refer [join]]))

;; TODO: implement two modes. 
;; 1. static single initialization for the global level
;; 2. dynamic rebinding or higher order functions https://stackoverflow.com/questions/11730828/clojure-and-dynamic
#_{:clj-kondo/ignore [:inline-def]}
(defn init! [{:keys [http-request-fn response-handler-fn
                     json-write-fn endpoint-url api-key api-token]
              :or {endpoint-url "https://api.trello.com/1/"
                   http-request-fn identity
                   response-handler-fn identity
                   json-write-fn identity}}]
  (def endpoint endpoint-url)
  (def http-request http-request-fn)
  (def http-response-handler response-handler-fn)
  (def json-write json-write-fn)

  (def required-query-params {:key api-key :token api-token})

  (def standard-request {:accept :json
                         :query-params required-query-params
                         :debug true
                         ;;:debug-body true
                         }))

(defn with-path-prefix [prefix id & segments]
  (->> segments
       (concat [prefix id])
       (map name)
       (join "/")
       (apply str)))

(defn endpoint-url [path] (str endpoint path))

(defn request
  ([method path] (request method path {} {}))
  ([method path params]
   (request method path {} params))
  ([method path body params]
   (let [req (merge-with into
                         standard-request
                         {:method method :url (endpoint-url path)}
                         {:query-params params}
                         (if (seq body)
                           {:content-type :json
                            :body (json-write body)}
                           {}))]
     (-> (http-request req)
         http-response-handler))))