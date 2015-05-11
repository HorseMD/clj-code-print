(defproject code-print "0.1.0-SNAPSHOT"
  :description "Organise code into a sort of book."
  :url "http://github.com/horsemd"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot code-print.core
  :profiles {:uberjar {:aot :all}})
