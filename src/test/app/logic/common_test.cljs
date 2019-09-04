(ns app.logic.common-test
  (:require [cljs.test :refer [deftest are]]
            [app.logic.common :as logic.common]))

(deftest icon-base-class
  (are [title base-class] (= (logic.common/icon-base-class title) base-class)
       :twitter "fab"
       :github "fab"
       :linkedin-in "fab"
       :speaker-deck "fab"
       :envelope "fal"
       :microphone-alt "fal"))
