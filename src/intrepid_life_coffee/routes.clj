(ns intrepid-life-coffee.routes
    (:require [intrepid-life-coffee.queries :as Q]
              [io.pedestal.service.http :as bootstrap]
              [io.pedestal.service.http.route :as route]
              [io.pedestal.service.log :as log]
              [io.pedestal.service.http.body-params :as body-params]
              [io.pedestal.service.http.route.definition :refer [defroutes]]
              [ring.util.response :as ring-resp]
              [datomic.api :as d]))

(defn home-page   [request]
  (ring-resp/response (concat "hello World!"
                              (Q/test-connection "test-ok"))))

(defroutes routes
  [[
    ["/" {:get home-page}
     ^:interceptors [(body-params/body-params) bootstrap/json-body] ;;builtin intercepter
     ]]])


(def service {:env :prod
              ::bootstrap/routes routes
              ;;::bootstrap/allowed-origins ["scheme://host:port"]
              ::bootstrap/resource-path "/public"
              ::bootstrap/type :jetty
              ::bootstrap/port 8080})
