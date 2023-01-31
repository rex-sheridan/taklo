(ns rex-sheridan.taklo.actions-test
     (:require [clojure.test :refer [deftest is use-fixtures]]
               [rex-sheridan.taklo.common :refer [init!]]
               [rex-sheridan.taklo.actions :as action]))

(use-fixtures :once (fn [f] (init! {}) (f)))

(def action-id "action-id")

(def base-request
  {:headers
   {"Authorization" "OAuth oauth_consumer_key=\"\", oauth_token=\"\""},
   :accept :json,
   :debug false,
   :debug-body true,
   :method :get,
   :url "https://api.trello.com/1/actions/action-id",
   :query-params nil})

(deftest get-action-test
  (is (= base-request (action/get-action action-id))))

(deftest update-action-test
  (is (=
       (assoc base-request
              :method :put
              :query-params {:text "action-text"})
       (action/update-action action-id "action-text"))))

(deftest delete-action-test
  (is (=
       (assoc base-request
              :method :delete
              :query-params {})
       (action/delete-action action-id))))