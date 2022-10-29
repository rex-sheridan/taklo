(ns rex-sheridan.taklo.members
    (:require [rex-sheridan.taklo.common :refer [request]]))

(defn get-my-boards
  "Get boards for the current user"
  [& [opts]]
  (request :get "members/me/boards" opts))

;; Members

(defn get-member [id-or-username]
  (request :get (str "members/" id-or-username) {}))

(defn get-members-orgs []
  (request :get "members/me/organizations"))