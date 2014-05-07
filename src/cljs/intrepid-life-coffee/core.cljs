(ns intrepid-life-coffee.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [clojure.string :as string]
            [reagent.core :as r]
            [cljs.core.async :refer (put! take! <! >! chan)])

  (:require-macros [cljs.core.async.macros :refer [go]])
  (:import (goog.events EventType)))

(def counter (r/atom 0))

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

(defn ^:export main []
  (let [message-channel (chan)]
    (go (loop []
          (print-message (<! message-channel))
          (recur)))
    (set-send-handler message-channel)))
