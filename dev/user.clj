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


   [clojure.edn :as edn]
   [datomic.api :as d]


   [intrepid-life-coffee.system :as sys]
   [intrepid-life-coffee.core]))

(defn init
  "Creates and initializes the system under development in the Var
  #'system."
  []
  (d/create-database (:uri (:db intrepid-life-coffee.core/config)))
  (intrepid-life-coffee.core/install-schema (sys/connection) "base"))

(defn start
  "Starts the system running, updates the Var #'system."
  []
  (intrepid-life-coffee.core/-main))

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
