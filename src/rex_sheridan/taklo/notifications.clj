(ns rex-sheridan.taklo.notifications
  (:require
   [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :notifications))

(defn get-notification [notification-id & [opts]]
  (request :get (path notification-id) opts))

(defn update-notifications-read-status [notification-id & [opts]]
  (request :put (path notification-id) opts))

(defn get-field-of-notification [notification-id field]
  (request :get (path notification-id field)))

(defn mark-all-notifications-as-read [notification-id & [opts]]
  (request :post (path notification-id :all :read) opts))

(defn update-notification-read-status [notification-id & [opts]]
  (request :put (path notification-id :unread) opts))

(defn get-board-for-notification [notification-id & [opts]]
  (request :get (path notification-id :board) opts))

(defn get-card-for-notification [notification-id & [opts]]
  (request :get (path notification-id :card) opts))

(defn get-list-for-notification [notification-id & [opts]]
  (request :get (path notification-id :list) opts))

(defn get-member-notification-is-about [notification-id & [opts]]
  (request :get (path notification-id :member) opts))

(defn get-member-creator-for-notification [notification-id & [opts]]
  (request :get (path notification-id :memberCreator) opts))

(defn get-notification-associated-organization [notification-id & [opts]]
  (request :get (path notification-id :organization) opts))


