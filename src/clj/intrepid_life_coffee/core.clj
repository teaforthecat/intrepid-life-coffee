(ns intrepid-life-coffee.core
  (:gen-class)
  (:require [intrepid-life-coffee.routes :as routes]
            [intrepid-life-coffee.system :as sys]
            [clojure.edn :as edn]
            [datomic.api :as d]
))

(def config
  (edn/read-string (slurp "config/system.edn")))

(defn connect [uri]
  (sys/set-connection (d/connect uri)))


(defn install-schema [conn schema-file]
  (let [schema (slurp (format "resources/schemas/%s.edn" schema-file))
      schema-tx (edn/read-string {:readers *data-readers*}
                                 schema)]
  @(d/transact conn schema-tx)))


(defn add-artwork-tx [title url artist]
  [{:db/id (d/tempid :db.part/user)
    :artwork/title title
    :artwork/url url
    :artwork/artist {:db/id [:artwork/name artist]}}])

(def artwork-query
  '[:find ?artwork ?name
    :in $ ?title
    :where
    [?artwork :artwork/title ?title]
    [?artwork :artwork/artist ?artist]
    [?artist :artist/name ?name]
    ])

(defn find-artwork-by-title [title]
  (let [db (d/db (sys/connection))]
    (d/q artwork-query
         db
         title)))

(defn -main [& args]
  (connect (:uri (:db config))))
