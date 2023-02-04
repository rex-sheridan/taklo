(ns rex-sheridan.taklo.webhooks-test
  (:require
   [clojure.test :refer [deftest is use-fixtures]]
   [rex-sheridan.taklo.webhooks :as subject]
   [rex-sheridan.taklo.common :refer [init!]]))

(use-fixtures :once (fn [f]
                      (init! {})
                      (f)))

(deftest create-webhook-test
  (let [callback-url "callback-url"
        model-id "model-id"]
    (is (= {:headers
            {"Authorization" "OAuth oauth_consumer_key=\"\", oauth_token=\"\""},
            :accept :json,
            :debug false,
            :debug-body false,
            :method :post,
            :url "https://api.trello.com/1/webhooks",
            :query-params {:callbackURL callback-url, :idModel model-id}}
           (subject/create-webhook callback-url model-id)
           ))))