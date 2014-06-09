(ns intrepid-life-coffee.routes
  ;; (:require [intrepid-life-coffee.queries :as Q]
  ;;           [ring.util.response :as ring-resp]
  ;;           [datomic.api :as d]))
  (:use intrepid-life-coffee.views
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [ring.middleware.params :refer (wrap-params)]
            [ring.middleware.logger :refer (wrap-with-logger)]
            [ring.middleware.keyword-params :refer (wrap-keyword-params)]
            [intrepid-life-coffee.system :as sys]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/say" {params :query-params} (say params))
  (route/resources "/" {:root (sys/resource-root)})
  (route/not-found "Page not found"))

(def app
  (sys/start
   (-> (handler/site main-routes)
       (wrap-with-logger)
       (wrap-params)
       (wrap-base-url))))
