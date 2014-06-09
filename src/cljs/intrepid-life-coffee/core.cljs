(ns intrepid-life-coffee.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [goog.net.XhrIo :as xhr]
            [clojure.string :as string]
            [reagent.core :as r]
            [ajax.core :refer [GET POST ajax-request raw-format]]
            [cljs.core.async :refer (put! take! <! >! chan broadcast)])

  (:require-macros [cljs.core.async.macros :refer [go]])
  (:import (goog.events EventType)))

(def counter (r/atom 0))
(def greeting (r/atom "nothin"))

(defn set-send-handler [channel]
  (events/listen
   (dom/getElement "main")
   EventType/CLICK
   (fn [event]
     (swap! counter inc)
     (let [message "test"]
       (put! channel message)))))

(defn print-message [message]
  (.log js/console (clj->js @counter)))

(defn greeting-component []
  [:div.greeting-component @greeting " "  @counter " times"]) ;;it remembers...

(defn greeting-display []
  [:div.greeting-display
   [greeting-component]])

(defn send-to-say [message]
  (let [receive-chan (chan)]
    (setup-receive-message receive-chan)
    (GET "/say" {:params {:greeting message}
                 :handler (fn [response]
                            (.log js/console  (str response))
                            (put! receive-chan response))
                 :response-format (raw-format)})))

(defn setup-receive-message [receive-chan]
  (go (loop []
        (when-let [greeting-message (<! receive-chan)]
          (reset! greeting greeting-message))
        (recur))))

(defn ^:export main []
  (let [message-channel (chan)
        say-channel (chan)]
    (go (loop []
          (let [number (<! message-channel)]
            (print-message number)
            (send-to-say number))
          (recur)))
    (set-send-handler message-channel)
    (r/render-component [greeting-display]
                        (.-body js/document))))
