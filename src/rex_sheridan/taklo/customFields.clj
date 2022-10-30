(ns rex-sheridan.taklo.customFields
  (:require
   [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :customFields))


(defn create-new-custom-field-on-board
  "Note that opts for this function refers to request body options rather than query params"
  [model-id name type pos & [opts]]
  (request :post "customFields"
           (merge {:idModel model-id
                   :modelType "board"
                   :name name
                   :type type
                   :pos pos} opts)
           {}))

(defn get-custom-field [custom-field-id]
  (request :get (path custom-field-id)))

(defn update-custom-field-definition
  "Note that opts for this function refers to request body options rather than query params"
  [custom-field-id & [opts]]
  (request :put (path custom-field-id) opts {}))

(defn delete-custom-field-definition [custom-field-id]
  (request :delete (path custom-field-id)))

(defn get-options-of-custom-field-drop-down [custom-field-id]
  (request :get (path custom-field-id :options)))

(defn add-option-to-custom-field-dropdown [custom-field-id & [opts]]
  (request :post (path custom-field-id :options) opts {}))

(defn get-option-of-custom-field-dropdown [custom-field-id option-id]
  (request :get (path custom-field-id :options option-id)))

(defn delete-option-of-custom-field-dropdown [custom-field-id option-id]
  (request :delete (path custom-field-id :options option-id)))