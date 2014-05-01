(ns intrepid-life-coffee.core
  (:gen-class)
  (:require [intrepid-life-coffee.routes :as routes]
            [intrepid-life-coffee.system :as sys]
            [clojure.edn :as edn]
            [datomic.api :as d]
            [io.pedestal.service-tools.server :as server]))

;; FIXME: has environment data
(defn connect []
  (alter-var-root  #'sys/system (fn [s]
                              (assoc s
                                :connection
                                (d/connect
                                 "datomic:riak://localhost:8087/intrepid-life-coffee/intrepid-life-coffee"
                                 )))))

(defn -main [& args]
  (connect)
  (server/init routes/service)
  (apply server/-main args)
  )
