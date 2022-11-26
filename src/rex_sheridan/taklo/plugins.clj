(ns rex-sheridan.taklo.plugins
  "https://developer.atlassian.com/cloud/trello/rest/api-group-plugins"
  (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :plugins))

(defn get-plugin [plugin-id]
  (request :get (path plugin-id)))

(defn update-plugin [plugin-id & [opts]]
  (request :put (path plugin-id) opts))

(defn create-listing-for-plugin [plugin-id & [opts]]
  (request :post (path plugin-id :listing) opts))

(defn get-plugins-member-privacy-compliance [plugin-id]
  (request :get (path plugin-id :compliance :memberPrivacy)))

(defn update-plugins-listing [plugin-id listing-id & [opts]]
  (request :put (path plugin-id :listings listing-id) opts))

