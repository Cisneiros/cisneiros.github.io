(ns app.logic.profile-test
  (:require [cljs.test :refer [deftest are]]
            [app.logic.profile :as logic.profile]))

(deftest contact-icon
  (are [id icon] (= (logic.profile/contact-icon id) icon)
       :email :envelope
       :github :github))

(deftest contact-label
  (are [id label] (= (logic.profile/contact-label id) label)
       :email "E-mail"
       :linkedin "LinkedIn"
       :something "something"))
