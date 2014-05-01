(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer (javadoc)]
   [clojure.pprint :refer (pprint)]
   [clojure.reflect :refer (reflect)]
   [clojure.repl :refer (apropos dir doc find-doc pst source)]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer (refresh refresh-all)]

   ;;added
   [clojurewerkz.welle.core :as wc]
   [clojurewerkz.welle.buckets :as wb]
   [clojurewerkz.welle.kv :as kv]

   [clojure.edn :as edn]
   [datomic.api :as d]


   [intrepid-life-coffee.core]))

(def system
  "A Var containing an object representing the application under
  development."
  nil)

(defn init
  "Creates and initializes the system under development in the Var
  #'system."
  []
  (wc/connect!)
  (let [app-name "intrepid-life-coffee"]
    (wb/create app-name)
    (kv/store app-name "config\\zookeeper" "127.0.0.1:2181" :content-type "text/plain"))
  )

(defn start
  "Starts the system running, updates the Var #'system."
  []
  (intrepid-life-coffee.core/connect)
  ;; (alter-var-root  #'system (fn [s]
  ;;                             (assoc s :connection  (let [uri "datomic:riak://localhost:8087/intrepid-life-coffee/intrepid-life-coffee"]
  ;;                                                     (d/create-database uri)
  ;;                                                     (d/connect uri)))))
  )

(defn stop
  "Stops the system if it is currently running, updates the Var
  #'system."
  []
  ;; TODO
  )

(defn go
  "Initializes and starts the system running."
  []
  (init)
  (start)
  :ready)

(defn reset
  "Stops the system, reloads modified source files, and restarts it."
  []
  (stop)
  (refresh :after 'user/go))
