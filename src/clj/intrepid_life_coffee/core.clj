(ns intrepid-life-coffee.core
  (:gen-class)
  (:require [intrepid-life-coffee.routes :as routes]
            [intrepid-life-coffee.system :as sys]
            [clojure.edn :as edn]
            [datomic.api :as d]
))

(def config
  (edn/read-string (slurp "config/system.edn")))

;; FIXME: has environment data
(defn connect []
  (alter-var-root  #'sys/system (fn [s]
                                  (assoc s :connection
                                         (d/connect (:uri (:db config))
                                                    )))))
(defn conn []
  (:connection sys/system))

(defn -main [& args]
  (connect)
  )
