(ns app.components.common
  (:require [app.layout :as layout]
            [app.logic.common :as logic.common]
            [herb.core :refer [<class defgroup]]
            [garden.units :refer [em rem px percent]]))

;; Section title

(defgroup section-title-style
  {:container {:position    #{:-webkit-sticky :sticky}
               :top         0
               :text-align  :left
               :color       (layout/color :primary)
               :background  (layout/color :background)
               :margin      "1rem 0 1rem"
               :font-weight 400}
   :text {:max-width      (percent 100)
          :width          (rem 10)
          :display        :block
          :padding-bottom (rem 0.2)
          :border-bottom  [[:dotted (px 1) (layout/color :primary)]]}})

(defn section-title [title]
  [:h1 {:class (<class section-title-style :container)}
   [:span {:class (<class section-title-style :text)}
    title]])

;; Item title

(defn- item-title-style []
  {:font-size     (em 1.6)
   :font-weight   400
   :margin-bottom (rem 0.2)})

(defn item-title [title]
  [:h2 {:class (<class item-title-style)} title])

;; Item description

(defn- item-description-style []
  {:font-size     (em 1)
   :margin-bottom (rem 0.2)})

(defn item-description [description]
  [:p {:class (<class item-description-style)} description])

;; Item subtext

(defn- item-subtext-style []
  {:font-size     (em 0.8)
   :margin-bottom (rem 0.2)})

(defn item-subtext [subtext]
  [:p {:class (<class item-subtext-style)} subtext])

;; Icon

(defn icon-style []
  {:white-space :nowrap})

(defn icon
  ([title]
   (let [base-class (logic.common/icon-base-class title)]
     [:i.fa-fw {:class [base-class (str "fa-" (name title))]}]))
  ([title & label]
   (into [:span {:class (<class icon-style)} [icon title] "\u00A0"] label)))
