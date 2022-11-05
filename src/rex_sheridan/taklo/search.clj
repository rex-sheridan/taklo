(ns rex-sheridan.taklo.search
  "https://developer.atlassian.com/cloud/trello/rest/api-group-search/#api-group-search"
  (:require [rex-sheridan.taklo.common :refer [request]]))


(defn search 
  "There's lots of options you can pass.
   See https://developer.atlassian.com/cloud/trello/rest/api-group-search/#api-search-get 
   for details"
  [query & [opts]]
  (request :get "search" (merge {:query query} opts)))

(defn search-members [query & [opts]]
  (request :get "search/members/"
           (merge {:query query} opts)))