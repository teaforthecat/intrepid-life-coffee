(ns intrepid-life-coffee.system
  (:require [datomic.api :as d]
            [clojure.edn :as edn]))


(def system
  "A Var containing an object representing the application"
  {:environment "dev"})

(defn set-connection [conn]
  (alter-var-root  #'system
                   #(assoc % :connection conn)))

(defn set-env []
  (alter-var-root #'system
                  #(assoc % :environment "dev")))

(defn set-app [app]
  (alter-var-root #'system
                  #(assoc % :app app)))

(defn get-app []
  (:app system))

;; this will need to be configured in project.clj as well for cljs-build
(defn resource-root []
  (case (:environment system)
    "dev" "public/dev"))

(defn connect [uri]
  (set-connection (d/connect uri)))

(defn connection []
  (:connection system))

(def config
  (edn/read-string (slurp "config/system.edn")))

(defn start [app]
  (set-app app)
  (connect (:uri (:db config)))
  app) ;;returns app
