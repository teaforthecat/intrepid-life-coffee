(defproject intrepid-life-coffee "0.1.0-SNAPSHOT"
  :description "website to advertise the coffee shop"
  :url "www.intrepidlifecoffee.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [io.pedestal/pedestal.service "0.2.2"]
                 [io.pedestal/pedestal.service-tools "0.2.2"]
                 [io.pedestal/pedestal.jetty "0.2.2"]

                 [ring/ring-json "0.2.0"]

                 [com.netflix.curator/curator-framework "1.2.3"]

                 [com.novemberain/welle "2.0.0"]
                 [com.datomic/datomic-pro "0.9.4714"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [com.cemerick/austin "0.1.4"]]
                   :source-paths ["dev"]}}

  :main ^{:skip-aot true} intrepid-life-coffee.core)
