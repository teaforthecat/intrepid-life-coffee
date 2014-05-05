(ns intrepid-life-coffee.core
  (:require [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer (put! take! <! >! chan)])
  (:require-macros [cljs.core.async.macros :refer [go go-loop alt!]])
  (:import (goog.events EventType)))

(defn set-send-handler [channel]
  (events/listen
   (dom/getElement "main")
   EventType/CLICK
   (fn [event]
     (let [message "test"]
       (put! channel message)))))

(defn print-message [message]
  (.log js/console message))

(defn ^:export main []
  (let [message-channel (chan)]
    (go (loop []
          (print-message (<! message-channel))
          (recur)))
    (set-send-handler message-channel)))
