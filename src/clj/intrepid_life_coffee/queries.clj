(ns intrepid-life-coffee.queries
  (:require [intrepid-life-coffee.system :refer [system]]
            [datomic.api :as d]))


(defn db []
  (d/db (:connection system)))

(defn last-tx []
  (d/next-t (db)))

(defn test-connection [test]
  "should return the same value passed in if connection is successful"
  (ffirst (d/q '[:find ?test :in $ ?test] (db) test)))
