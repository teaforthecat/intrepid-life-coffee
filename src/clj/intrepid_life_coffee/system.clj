(ns intrepid-life-coffee.system)


(def system
  "A Var containing an object representing the application"
  nil)

(defn set-connection [conn]
  (alter-var-root  #'system
                   #(assoc % :connection conn)))

(defn connection []
  (:connection system))
