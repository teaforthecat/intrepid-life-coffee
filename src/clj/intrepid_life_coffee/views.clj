(ns intrepid-life-coffee.views
  (:require [clojure.tools.logging :as l]
            [intrepid-life-coffee.core :refer (save-say)])
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
     (include-js "http://fb.me/react-0.9.0.js")
     ;; (include-js "/js/goog/base.js") ;;for :none
     (include-js "/js/main.js")
     ;; (include-js "/js/intrepid_life_coffee/core.js") ;;for :none
     [:script "intrepid_life_coffee.core.main()"]
     ]))



(defn say [params]
  (when-let [greeting (get params "greeting")]
    (let [result (save-say greeting)]
      (if (:success result)
        (html [:p "You nsaid: %s" greeting " " (:number result) " times"  ])
        (html [:p.error "there was an error"])))))
