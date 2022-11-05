(ns rex-sheridan.taklo.emoji
  (:require [rex-sheridan.taklo.common :refer [request]]))

(defn get-emoji [& [opts]]
  (request :get "emoji" opts))