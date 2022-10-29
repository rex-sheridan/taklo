(ns rex-sheridan.taklo.checklists
  (:require
   [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :checklists))

(defn create-checklist [card-id & [opts]]
  (request :post (str "checklists" card-id) 
           (merge {:idCard card-id} opts)))

(defn get-checklist [checklist-id & [opts]]
  (request :get (path checklist-id) opts))

(defn update-checklist [checklist-id & [opts]]
  (request :put (path checklist-id) opts))

(defn delete-checklist [checklist-id & [opts]]
  (request :delete (path checklist-id) opts))

(defn get-field-on-checklist [checklist-id field]
  (request :get (path checklist-id field)))

(defn update-field-on-checklist [checklist-id field value]
  (request :put (path checklist-id field) {:value value}))

(defn get-board-for-checklist [checklist-id & [opts]]
  (request :get (path checklist-id :board) opts))

(defn get-card-for-checklist [checklist-id]
  (request :get (path checklist-id :cards)))

(defn get-checkitems-on-checklist [checklist-id & [opts]]
  (request :get (path checklist-id :checkItems) opts))

(defn create-checkitem-on-checklist [checklist-id name & [opts]]
  (request :post (path checklist-id :checkItems)
           (merge {:name name} opts)))

(defn get-checkitem-on-checklist [checklist-id checkitem-id & [opts]]
  (request :get (path checklist-id :checkItems checkitem-id) opts))

(defn delete-checkitem-from-checklist [checklist-id checkitem-id]
  (request :delete (path checklist-id :checkItems checkitem-id)))
