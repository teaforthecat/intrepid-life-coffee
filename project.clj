(defproject intrepid-life-coffee "0.1.0-SNAPSHOT"
  :description "website to advertise the coffee shop"
  :url "www.intrepidlifecoffee.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring "1.2.1"]
                 [hiccup "1.0.5"]
                 [org.slf4j/log4j-over-slf4j "1.6.6"] ;;avoids dep loop "Class path contains multiple SLF4J bindings"
                 [com.datomic/datomic-pro "0.9.4714"]
                 [org.clojars.magomimmo/core.async "0.1.0-SNAPSHOT"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [reagent "0.2.0"]
                 ]

;;; why doesn't this get picked up?
  ;; :dev-dependencies [[org.clojure/clojurescript "0.0-2202"]
  ;;                    [reagent "0.2.0"]]
  ;; for running riak
  :prod-dependencies [[com.netflix.curator/curator-framework "1.2.3"]]

  :ring {:handler intrepid-life-coffee.routes/app}
  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["config", "resources"]
  ;; :plugins [[lein-cljsbuild "1.0.1"]
  ;;           [lein-ring "0.8.10"]]

  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [com.cemerick/austin "0.1.4"]]
                   :source-paths ["dev"]}}

  :cljsbuild {:test-commands {"unit-tests" ["runners/phantomjs.js" :runner
                                            "window.literal_js_executed=true"
                                            "test-cljs/vendor/es5-shim.js"
                                            "test-cljs/vendor/es5-sham.js"
                                            "test-cljs/vendor/console-polyfill.js"
                                            "resources/private/js/unit-test.js"]}
              :builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :compiler {:optimizations :whitespace
                                   :output-to "resources/public/dev/js/main.js"
                                   :output-dir "resources/public/dev/js"
                                   :preamble ["reagent/react.js"]
                                   :pretty-print true}}
                       ]}

  :hooks [leiningen.cljsbuild] )
