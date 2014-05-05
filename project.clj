(defproject intrepid-life-coffee "0.1.0-SNAPSHOT"
  :description "website to advertise the coffee shop"
  :url "www.intrepidlifecoffee.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]

                 [compojure "1.1.6"]
                 [ring "1.2.1"]
                 [hiccup "1.0.5"]
                 [org.slf4j/log4j-over-slf4j "1.6.6"] ;;avoids dep loop
                 ;; [org.clojars.kjw/slf4j "1.5.5"]
                 [com.netflix.curator/curator-framework "1.2.3"]

                 [com.novemberain/welle "2.0.0"]
                 [com.datomic/datomic-pro "0.9.4714"]
                 [org.clojars.magomimmo/core.async "0.1.0-SNAPSHOT"]
                 ;cljs
                 [org.clojure/clojurescript "0.0-2202"]
                 [reagent "0.4.2"]

                 ]
  :ring {:handler intrepid-life-coffee.routes/app}
  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["config", "resources"]


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
                             :compiler {:optimizations :none
                                        :output-to "resources/public/dev/js/main.js"
                                        :output-dir "resources/public/dev/js" }}
                       ]}

  :hooks [leiningen.cljsbuild]

;  :main ^{:skip-aot true} intrepid-life-coffee.core
)
