(ns intrepid-life-coffee.core
  (:gen-class)
  (:require [intrepid-life-coffee.system :as sys]
            [clojure.edn :as edn]
            [datomic.api :as d]))

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

(defn insert-say-tx [greeting]
  [{:db/id (d/tempid :db.part/user)
    :greeting/message greeting}])

(def greeting-message-count-query
  '[:find ?hit-count
    :in $ ?greeting
    :where [?e :greeting/message ?greeting
            ?e :greeting/hit-count ?hit-count]])

(defn save-say [greeting]
  (let [conn (sys/connection)
        db   (d/db conn)
        tx   (insert-say-tx greeting)
        result @(d/transact conn tx)]
    (let [db (:db-after result)]
      {:success true
       :number (d/q greeting-message-count-query db greeting)})))

(def artwork-query
  '[:find ?artwork ?name
    :in $ ?title
    :where
    [?artwork :artwork/title ?title]
    [?artwork :artwork/artist ?artist]
    [?artist :artist/name ?name]
    ])

(def artist-query
  '[:find ?artist
    :in $ ?name
    :where
    [?artist :artist/name ?name]])

(defn realize [db id]
  (seq (d/entity db id)))

(defn find-artist-by-name [name]
  (let [db (d/db (sys/connection))]
    (realize db (ffirst (d/q artist-query
                           db
                           name)))))

(defn get-db []
  (d/db (sys/connection)))

(defn find-artwork-by-title [title]
  (let [db (d/db (sys/connection))]
    (d/q artwork-query
         db
         title)))

(def find-attribute-q
  '[:find ?s
    :in $ ?ident
    :where
    [?s :db/ident ?ident]])

(defn show-schema [ident]
  (let [db (d/db (sys/connection))
        attr (d/q find-attribute-q db ident)]
    (realize db (ffirst attr))))

(defn -main [& args]
  (connect (:uri (:db config))))
