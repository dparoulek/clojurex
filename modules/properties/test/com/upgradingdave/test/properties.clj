(ns com.upgradingdave.test.properties
  (:use clojure.test
        com.upgradingdave.properties))

;; Here's what we expect the result of converting the properties file
;; to clojure datastructures to look like
(def expect1 {:avector ["cool" "stuff"], :astring "bar", :anum 99.9})

(deftest test-properties
  (testing "Load properties from classpath"
    (is (= expect1 (from-classpath "test.properties")))
    (is (= expect1 (from-filepath
                    (str (System/getProperty "user.dir") "/config/test.properties"))))
))