(ns rex-sheridan.taklo.common-test
   (:require [clojure.test :refer [deftest is use-fixtures testing]]
             [rex-sheridan.taklo.common
              :refer [create-authorization
                      default-endpoint-url
                      endpoint-url
                      init!
                      with-path-prefix
                      request]]))

(use-fixtures :once (fn [f]
                      (init! {})
                      (f)))

(deftest request-test
  (let [base-request {:headers
                      {"Authorization" "OAuth oauth_consumer_key=\"\", oauth_token=\"\""},
                      :accept :json,
                      :debug false,
                      :debug-body false,
                      :method :get,
                      :url "https://api.trello.com/1/path",
                      :query-params {}}
        path "path"]

    (testing "Builds requests with method and path"
      (is (= base-request (request :get path)))
      (is (= (merge base-request {:method :post}) (request :post path)))
      (is (= (merge base-request {:method :delete}) (request :delete path))))
    (testing "Doesn't prevent invalid methods"
      (is (= (merge base-request {:method :invalid-but-allowed}) (request :invalid-but-allowed path))))
    (testing "Doesn't attempt to convert keywords"
      (is (= (merge base-request {:method :delete
                                  :url "https://api.trello.com/1/:path"}) (request :delete :path))))
    (testing "Adds query parameters"
      (is (= (merge base-request {:query-params {:foo "bar"}}) (request :get path {:foo "bar"}))))
    (testing "Adds body"
      (is (= (merge base-request
                    {:method :post
                     :query-params {:foo "bar"}
                     :body {:contents :one}
                     :content-type :json}) (request :post path {:contents :one} {:foo "bar"})))
      )))

(deftest with-path-prefix-test
  (testing "Builds paths"
    (let [prefix "prefix"
          id "id"]
      (is (= (str prefix "/" id)
             (with-path-prefix prefix id)))
      (is (= (str prefix "/" id "/" "first" "/" "second")
             (with-path-prefix prefix id "first" :second))))))

(deftest authorization-test
  (testing "Authorization headers"
    (let [api-key "api-key"
          api-token "api-token"]
      (is (= {"Authorization" "OAuth oauth_consumer_key=\"api-key\", oauth_token=\"api-token\""}
             (create-authorization api-key api-token))))))

(deftest endpoint-test
  (testing "Context of the test assertions" 
      (let [path "path"]
        (binding [rex-sheridan.taklo.common/*endpoint* "rebound endpoint"]
          (is (not= (str default-endpoint-url path) (endpoint-url "path"))))
        (is (= (str default-endpoint-url path) (endpoint-url "path"))))))