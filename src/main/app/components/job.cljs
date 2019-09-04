(ns app.components.job
  (:require [app.components.common :as components.common]
            [app.logic.time :as logic.time]
            [app.layout :as layout]
            [herb.core :refer [<class defgroup]]
            [garden.units :refer [rem percent]]))

;; Job item

(defn- job-item-style []
  ^{:media {(layout/media-query :medium) {:width (percent 50)}}}
  {:padding [[0 0 (rem 1)]]})

(defn- job-item [[id {:keys [company title location started ended]}]]
  (let [time-label (if-not ended
                     (str "Since " (logic.time/->year-month started))
                     (str "From " (logic.time/->year-month started) " to " (logic.time/->year-month ended)))]
    ^{:key id}
    [:article {:class (<class job-item-style)}
     [components.common/item-title company]
     [components.common/item-description title]
     [components.common/item-subtext
      [:span
       [components.common/icon :calendar-day time-label] " "
       [components.common/icon :map-marker-alt location]]]]))

;; Job list

(defgroup job-list-style
  {:current {:font-size (rem 1)}
   :past    {:display         :flex
             :justify-content :space-between
             :flex-wrap       :wrap
             :font-size       (rem 0.8)}})

(defn job-list [jobs]
  (let [current-jobs (logic.time/reverse-sort-by-date (filter (complement (comp :ended second)) jobs))
        past-jobs (logic.time/reverse-sort-by-date (filter (comp :ended second) jobs))]
    [:<>
     [:section {:class (<class job-list-style :current)}
      (map job-item current-jobs)]
     [components.common/section-title "Previously…"]
     [:section {:class (<class job-list-style :past)}
      (map job-item past-jobs)]]))
