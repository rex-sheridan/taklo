(ns rex-sheridan.taklo.webhooks
  "https://developer.atlassian.com/cloud/trello/rest/api-group-webhooks"
    (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :webhooks))

(defn create-webhook [webhook-id callback-url model-id & [opts]]
  (request :post "webhooks" (merge {:callbackURL callback-url
                                    :idModel model-id} opts)))

(defn get-webhook [webhook-id]
  (request :get (path webhook-id)))

(defn update-webhook [webhook-id & [opts]]
  (request :put (path webhook-id) opts))

(defn delete-webhook [webhook-id]
  (request :delete (path webhook-id)))

(defn get-field-on-webhook [webhook-id field]
  (request :get (path webhook-id field)))

