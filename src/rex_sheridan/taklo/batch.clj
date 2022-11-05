(ns rex-sheridan.taklo.batch
  "https://developer.atlassian.com/cloud/trello/rest/api-group-batch/#api-group-batch"
    (:require [rex-sheridan.taklo.common :refer [request]]))

(defn batch-get [urls]
  (request :get "batch" {:urls urls}))