(ns app.components.app
  (:require [app.components.common :as components.common]
            [app.components.profile :as components.profile]
            [app.components.job :as components.job]
            [app.components.event :as components.event]
            [app.layout :as layout]
            [herb.core :refer [<class]]
            [garden.units :refer [rem px]]))

(defn app-style []
  ^{:media {(layout/media-query :large) {:max-width (px 980)
                                   :margin    [[0 :auto]]
                                   :padding   0}}}
  {:padding [[0 (rem 1)]]})

(defn app [{:keys [profile jobs events]}]
  [:main {:class (<class app-style)}
   [components.common/section-title "I am"]
   [components.profile/profile-item profile]

   [components.common/section-title "I work at"]
   [components.job/job-list jobs]

   [components.common/section-title "Events and talks"]
   [components.event/event-list events]

   [components.common/section-title "Find me at"]
   [components.profile/contact-list profile]])
