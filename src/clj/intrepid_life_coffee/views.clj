(ns intrepid-life-coffee.views
  (:use [hiccup core page]))

(defn index-page []
  (html5
    [:head
      [:title "Hello World"]

      (include-css "//cdnjs.cloudflare.com/ajax/libs/foundation/5.2.2/css/foundation.min.css")
      (include-css "/css/style.css")
     ]
    [:body#main
      [:h1 "Hello World"]
     (include-js "/js/goog/base.js")
     (include-js "/js/main.js")
     (include-js "/js/intrepid_life_coffee/core.js")
     [:script "intrepid_life_coffee.core.main();"]
     ]))