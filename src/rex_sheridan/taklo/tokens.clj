(ns rex-sheridan.taklo.tokens
  "https://developer.atlassian.com/cloud/trello/rest/api-group-tokens"
    (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :tokens))

(defn get-token [token-id & [opts]]
  (request :get (path token-id) opts))

(defn get-tokens-member [token-id & [opts]]
  (request :get (path token-id :member) opts))

(defn get-webhooks-for-token [token-id]
  (request :get (path token-id :webhooks)))

(defn create-webhooks-for-token [token-id callback-url model-id & [opts]]
  (request :post (path token-id :webhooks) 
           (merge {:callbackURL callback-url :idModel model-id} opts)))

(defn get-webhook-belonging-to-token [token-id webhook-id]
  (request :get (path token-id :webhooks webhook-id)))

(defn update-webhook-created-by-token [token-id webhook-id & [opts]]
  (request :put (path token-id :webhooks webhook-id) opts))

(defn delete-webhook-created-by-token [token-id webhook-id]
  (request :delete (path token-id :webhooks webhook-id)))

(defn delete-token [token-id]
  (request :delete (path token-id)))

