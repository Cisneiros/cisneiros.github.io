(ns app.components.profile
  (:require [app.components.common :as components.common]
            [app.logic.profile :as logic.profile]
            [herb.core :refer [<class defgroup]]
            [garden.units :refer [rem]]
            [app.layout :as layout]))

;; Profile item

(defn- profile-item-style []
  {:margin-bottom "2rem"})

(defn profile-item [{:keys [full-name nickname bio location]}]
  [:section {:class (<class profile-item-style)}
   [components.common/item-title full-name]
   [components.common/item-subtext
    [:span
     [components.common/icon :user "a.k.a. " nickname] " "
     [components.common/icon :map-marker-alt location]]]
   [components.common/item-description bio]])

;; Contact item

(defgroup contact-item-style
  {:container {:padding [[(rem 1) 0]]
               :font-size (rem 0.7)
               :text-align :center
               :text-transform :uppercase}
   :icon      ^{:media {(layout/media-query :medium) {:font-size (rem 4)}}}
              {:font-size   (rem 3)
               :text-shadow [["0.2rem 0.2rem 0" (layout/color :background-accent)]]}})

(defn contact-item [[id url]]
  ^{:key id}
  [:li {:class (<class contact-item-style :container)}
   [:a {:href url}
    [:p {:class (<class contact-item-style :icon)} [components.common/icon (logic.profile/contact-icon id)]]
    [:p (logic.profile/contact-label id)]]])


;; Contact list

(defn- contact-list-style []
  {:display         :flex
   :justify-content :space-around
   :flex-align      :center
   :flex-wrap       :wrap})

(defn contact-list [{:keys [contacts]}]
  [:section
   [:ul {:class (<class contact-list-style)}
    (map contact-item contacts)]])

