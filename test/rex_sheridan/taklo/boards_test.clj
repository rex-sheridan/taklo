(ns rex-sheridan.taklo.boards-test
   (:require [clojure.test :refer [deftest is use-fixtures]]
             [rex-sheridan.taklo.common :refer [init!]]
             [rex-sheridan.taklo.boards :refer [get-board]]))

(def api-key "api-key")
(def api-token "api-token")

(use-fixtures :once (fn [f]
                      (init! {:api-key api-key
                              :api-token api-token})
                      (f)))

(deftest boards 
  (let [board-id "board-id"]
    (is (=
         (get-board board-id {:foo "bar"})
         {:headers
          {"Authorization"
           "OAuth oauth_consumer_key=\"api-key\", oauth_token=\"api-token\""},
          :accept :json,
          :debug false,
          :debug-body false,
          :method :get,
          :url "https://api.trello.com/1/boards/board-id",
          :query-params {:foo "bar"}}))))
