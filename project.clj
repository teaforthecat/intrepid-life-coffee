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

;; Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.io.ByteStreams.limit(Ljava/io/InputStream;J)Ljava/io/InputStream;
;;      at com.google.javascript.jscomp.CommandLineRunner.getDefaultExterns(CommandLineRunner.java:939)
;;      at cljs.closure$load_externs.invoke(closure.clj:235)
;;      at cljs.closure$optimize.doInvoke(closure.clj:769)
;;      at clojure.lang.RestFn.applyTo(RestFn.java:139)
;;      at clojure.core$apply.invoke(core.clj:619)

                 ;; [com.netflix.curator/curator-framework "1.2.3"]
                 ;; [com.rmoquin.bundle/curator-framework "1.0.1"]
                 [org.apache.curator/curator-framework "2.4.2"]
                 ;; this results in: ClassNotFoundException com.netflix.curator.framework.CuratorFramework  java.net.URLClassLoader$1.run (URLClassLoader.java:366)

                 [com.novemberain/welle "2.0.0"]
                 [com.datomic/datomic-pro "0.9.4714"]
                 [org.clojars.magomimmo/core.async "0.1.0-SNAPSHOT"]
                 ;;ummm what? maybe? http://dev.clojure.org/jira/browse/CLJS-790
                 ;; [org.clojure/google-closure-library "0.0-20130212-95c19e7f0f5f"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [reagent "0.2.0"]
                 ]
  :ring {:handler intrepid-life-coffee.routes/app}
  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["config", "resources"]
  :plugins [[lein-cljsbuild "1.0.1"]]


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

  :hooks [leiningen.cljsbuild]

;  :main ^{:skip-aot true} intrepid-life-coffee.core
)
