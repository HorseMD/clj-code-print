(ns code-print.core-test
  (:require [clojure.test :refer :all]
            [code-print.core :refer :all]
            [clojure.java.io :as io]))

(deftest get-extension-test
  (testing "Getting a file's extension."
    (is (= (get-extension (io/file "./src/core.clj")) "clj"))
    (is (= (get-extension (io/file ".gitignore")) "gitignore"))))

(deftest dir-empty?-test
  (testing "Checking a dir is empty."
    (is (= (dir-empty? (io/file ".")) false))))

(deftest get-language-test
  (testing "Getting appropriate language for a file."
    (is (= (get-language (io/file "./src/core.clj")) "clojure"))
    (is (= (get-language (io/file "./README.md")) "markdown"))))
