(ns app.logic.event-test
  (:require [cljs.test :refer [deftest are]]
            [app.logic.event :as logic.event]))

(deftest role-icon
  (are [role icon] (= (logic.event/role-icon role) icon)
       :speaker :presentation
       :presenter :microphone-alt
       :something :badge))

(deftest role-label
  (are [role label] (= (logic.event/role-label role) label)
       :speaker "Speaker"
       :presenter "Presenter"
       :something "something"))
