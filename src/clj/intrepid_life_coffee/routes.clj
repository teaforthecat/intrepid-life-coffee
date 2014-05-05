(ns intrepid-life-coffee.routes
  ;; (:require [intrepid-life-coffee.queries :as Q]
  ;;           [ring.util.response :as ring-resp]
  ;;           [datomic.api :as d]))
  (:use intrepid-life-coffee.views
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.core :refer [defroutes GET]]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (route/resources "/" {:root "public/dev"})
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))
