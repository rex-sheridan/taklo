(ns rex-sheridan.taklo.actions
  "https://developer.atlassian.com/cloud/trello/rest/api-group-actions"
  (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :actions))

(defn get-action [action-id & [opts]] 
  (request :get (path action-id) opts))

(defn update-action [action-id text & [opts]] 
  (request :put (path action-id) (merge {:text text} opts)))

(defn delete-action [action-id] 
  (request :delete (path action-id)))

(defn get-specific-field-on-action [action-id field] 
  (request :get (path action-id field)))

(defn get-board-for-action [action-id & [opts]] 
  (request :get (path action-id :board) opts))

(defn get-card-for-action [action-id & [opts]] 
  (request :get (path action-id :card) opts))

(defn get-list-for-action [action-id & [opts]] 
  (request :get (path action-id :list) opts))

(defn get-member-for-action [action-id & [opts]] 
  (request :get (path action-id :member) opts))

(defn get-member-creator-of-action [action-id & [opts]] 
  (request :get (path action-id :memberCreator) opts))

(defn get-organization-of-action [action-id & [opts]] 
  (request :get (path action-id :organization) opts))

(defn update-comment-action [action-id comment & [opts]] 
  (request :put (path action-id :text) (merge {:value comment} opts)))

(defn get-actions-reactions [action-id & [opts]] 
  (request :get (path action-id :reactions) opts))

(defn create-reaction-for-action [action-id reaction-body] 
  (request :post (path action-id :reactions) reaction-body {}))

(defn get-actions-reaction [action-id reaction-id & [opts]] 
  (request :get (path action-id :reactions reaction-id) opts))

(defn delete-actions-reaction [action-id reaction-id] 
  (request :delete (path action-id :reactions reaction-id)))

(defn list-actions-summary-of-reactions [action-id] 
  (request :get (path action-id :reactionsSummary)))
