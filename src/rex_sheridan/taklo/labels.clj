(ns rex-sheridan.taklo.labels
  "https://developer.atlassian.com/cloud/trello/rest/api-group-labels"
  (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :labels))

(defn get-label [label-id & [opts]]
  (request :get (path label-id) opts))

(defn update-label [label-id & [opts]]
  (request :put (path label-id) opts))

(defn delete-label [label-id]
  (request :delete (path label-id)))

(defn update-field-on-label [label-id field value ]
  (request :put (path label-id field) {:value value}))

(defn create-label [name color board-id]
  (request :post "labels" 
           {:name name
            :color color
            :idBoard board-id}))