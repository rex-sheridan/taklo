(ns rex-sheridan.taklo.lists
  "https://developer.atlassian.com/cloud/trello/rest/api-group-lists"
   (:require
    [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :lists))

(defn get-list [list-id & [opts]]
  (request :get (path list-id) opts))

(defn update-list [list-id & [opts]]
  (request :put (path list-id) opts))

(defn create-list [board-id name & [opts]]
  (request :post "lists" (merge {:idBoard board-id
                                 :name name}
                                opts)))

(defn archive-all-cards-in-list [list-id]
  (request :post (path list-id :archiveAllCards)))

(defn move-all-cards-in-list [source-list-id board-id target-list-id]
  (request :post (path source-list-id :moveAllCards)
           {:idBoard board-id :idList target-list-id}))

(defn archive-list [list-id closed]
  (request :put (path list-id :closed) {:value closed}))

(defn get-cards-by-list [list-id & [opts]]
  (request :get (path list-id :cards) opts))

(defn move-list-to-board [list-id board-id]
  (request :put (path list-id :idBoard) {:value board-id}))

(defn update-field-on-list [list-id field value]
  (request :put (path list-id field) {:value value}))

(defn get-actions-on-list [list-id & [opts]]
  (request :get (path list-id :actions) opts))

(defn get-board-for-list [list-id & [opts]]
  (request :get (path list-id :board) opts))