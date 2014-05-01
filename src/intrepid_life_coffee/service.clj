(ns intrepid-life-coffee.service
  (:require [intrepid-life-coffee.routes :refer [routes]]
            [io.pedestal.service.http :as bootstrap]))


;; FIXME: has environment data
(def service {:env :prod
              ::bootstrap/routes routes
              ;;::bootstrap/allowed-origins ["scheme://host:port"]
              ::bootstrap/resource-path "/public"
              ::bootstrap/type :jetty
              ::bootstrap/port 8080})
