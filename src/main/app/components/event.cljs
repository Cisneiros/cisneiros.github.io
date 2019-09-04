(ns app.components.event
  (:require [app.components.common :as components.common]
            [app.logic.event :as logic.event]
            [app.logic.time :as logic.time]
            [app.layout :as layout]
            [herb.core :refer [<class defgroup]]
            [garden.units :refer [em rem percent]]))

;; Event calendar

(defgroup event-calendar-style
  {:container  ^{:media {(layout/media-query :medium) {:width     (rem 3)
                                                       :font-size (rem 1.5)}}}
               {:display        :flex
                :flex-direction :column
                :flex-shrink    0
                :width          (rem 2)
                :font-size      (rem 1)
                :text-align     :center                     ;;:border         "solid 0 1px 1px 0 #fefffe"
                :background     (layout/color :background)
                :box-shadow     [["0.2rem 0.2rem 0 0" (layout/color :background-accent)]]}
   :year-month {:background     (layout/color :primary)
                :color          (layout/color :background)
                :font-size      (em 0.55)
                :font-weight    400
                :text-transform :uppercase
                :padding        [[(rem 0.1 0)]]}
   :day        {:font-size (em 1.5)}})

(defn- event-calendar [date]
  [:div {:class (<class event-calendar-style :container)}
   [:div {:class (<class event-calendar-style :year-month)}
    (logic.time/->year date)
    [:br]
    (logic.time/->month-name date)]
   [:div {:class (<class event-calendar-style :day)}
    (logic.time/->day date)]])

;; Event role

(defgroup event-role-style
  {:container ^{:media {(layout/media-query :medium) {:font-size (rem 3)}}}
              {:display        :inline-block
               :font-size      (rem 2)
               :margin-left    (rem 1)
               :text-align     :center
               :text-transform :uppercase
               :color          (layout/color :primary)}
   :icon      {:text-shadow [["0.2rem 0.2rem 0" (layout/color :background-accent)]]}
   :label     {:font-size  (rem 0.7)
               :margin-top (rem 0.2)}})

(defn- event-role [role]
  ^{:key role}
  (let [icon (logic.event/role-icon role)
        label (logic.event/role-label role)]
    ^{:key role}
    [:div {:class (<class event-role-style :container)}
     [:span {:class (<class event-role-style :icon)} [components.common/icon icon]]
     [:p {:class (<class event-role-style :label)} label]]))

;; Event session

(defn- event-session [{:keys [title slides video tracks]}]
  ^{:key title}
  [components.common/item-description
   [:<> title
    (if slides [:<> " " [:a {:href slides}
                         [components.common/icon :presentation "Slides"]]])
    (if video [:<> " " [:a {:href video}
                        [components.common/icon :video "Video"]]])]])

;; Event subtext

(defn- event-subtext [location links]
  [components.common/item-subtext
   [:<> [components.common/icon :map-marker-alt location]
    (map (fn [{:keys [kind url]}]
           ^{:key url}
           [:span " " [:a {:href url}
                     [components.common/icon :link (name kind)]]]) links)]])

;; Event item

(defgroup event-item-style
  {:container ^{:media {(layout/media-query :medium) {:flex-wrap   :nowrap
                                                      :align-items :flex-start
                                                      :padding     [[0 0 (rem 1)]]}}}
              {:padding        [[0 0 (rem 2)]]
               :display        :flex
               :flex-direction :row
               :flex-wrap      :wrap
               :align-items    :center}
   :text      ^{:media {(layout/media-query :medium) {:margin-left (rem 1)
                                                      :order       0}}}
              {:flex-grow 1
               :width     (percent 100)
               :order     -1}
   :roles     ^{:media {(layout/media-query :medium) {:width :auto}}}
              {
               :flex-shrink 0}})

(defn- event-item [[id {:keys [title date sessions description roles location links]}]]
  ^{:key id}
  [:article {:class (<class event-item-style :container)}
   [event-calendar date]
   [:div {:class (<class event-item-style :text)}
    [components.common/item-title title]
    (if description
      [components.common/item-description description])
    (map event-session sessions)
    [event-subtext location links]]
   [:div {:class (<class event-item-style :roles)} (map event-role roles)]])

(defn event-list [events]
  (let [sorted-events (logic.time/reverse-sort-by-date events)]
    [:<> (map event-item sorted-events)]))
