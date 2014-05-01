(defproject intrepid-life-coffee "0.1.0-SNAPSHOT"
  :description "website to advertise the coffee shop"
  :url "www.intrepidlifecoffee.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]

                 ;; [compojure "1.1.6"]

                 [io.pedestal/pedestal.service "0.2.2"]
                 [io.pedestal/pedestal.service-tools "0.2.2"]
                 [io.pedestal/pedestal.jetty "0.2.2"]

                 [ring/ring-json "0.2.0"]

                 [com.netflix.curator/curator-framework "1.2.3"]

                 [com.novemberain/welle "2.0.0"]
                 [com.datomic/datomic-pro "0.9.4714"]

                 ;cljs
                 [org.clojure/clojurescript "0.0-2202"]
                 [reagent "0.4.2"]

                 ]

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
                        :compiler {:output-to "resources/public/js/dev/main.js"
                                   :output-dir "resources/public/js/dev"
                                   :optimizations :none
                                   :source-map true
                                   :source-map-prefix "js/dev"}}
                       ;; {
                       ;;  ;; :source-paths ["src"
                       ;;                 ;; "test-cljs"]
                       ;;  :id "test"
                       ;;  :compiler {:pretty-print true
                       ;;             :output-dir "resources/private/js/"
                       ;;             :output-to "resources/private/js/unit-test.js"
                       ;;             ;; :preamble ["react/react.js"]
                       ;;             ;; :externs ["react/externs/react.js"]
                       ;;             :optimizations :whitespace}}
                       ;; {:id "prod"
                       ;;  ;; :source-paths ["src"]
                       ;;  :compiler {:output-to "main.js"
                       ;;             :output-dir "resources/public"
                       ;;             :optimizations :advanced
                       ;;             :source-map "main.js.map"
                       ;;             :pretty-print false
                       ;;             :preamble ["react/react.min.js"]
                       ;;             :externs ["react/externs/react.js"]}}
                       ]}

  :hooks [leiningen.cljsbuild]

  :main ^{:skip-aot true} intrepid-life-coffee.core)
