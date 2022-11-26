(ns rex-sheridan.taklo.emoji
  "https://developer.atlassian.com/cloud/trello/rest/api-group-emoji/"
  (:require [rex-sheridan.taklo.common :refer [request]]))

(defn get-emoji [& [opts]]
  (request :get "emoji" opts))