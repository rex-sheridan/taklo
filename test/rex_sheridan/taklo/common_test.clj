(ns rex-sheridan.taklo.common-test
   (:require [clojure.test :refer :all]
             [rex-sheridan.taklo.common :refer :all]))

(use-fixtures :once (fn [f]
                      (init! {})
                      (f)))

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
        (binding [rex-sheridan.taklo.common/endpoint "rebound endpoint"]
          (is (not= (str default-endpoint-url path) (endpoint-url "path"))))
        (is (= (str default-endpoint-url path) (endpoint-url "path"))))))