(ns rex-sheridan.taklo.applications
  "https://developer.atlassian.com/cloud/trello/rest/api-group-applications"
  (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :applications))

(defn get-applications-compliance-data [application-key]
  (request :get (path application-key :compliance)))