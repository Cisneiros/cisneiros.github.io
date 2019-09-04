(ns app.logic.time
  (:require [cljs-time.format :as time.format]
            [cljs-time.core :as time]))

(defn- reverse-sort-by-date [things]
  (sort-by (comp :date second) > things))

(defn- ->year-month [local-date]
  (time.format/unparse-local-date (time.format/formatter "MMM YYYY") local-date))

(defn ->month-name [local-date]
  (time.format/unparse-local-date (time.format/formatter "MMM") local-date))

(defn ->year [local-date]
  (time/year local-date))

(defn ->day [local-date]
  (time/day local-date))

(defn ->local-date
  ([v]
    (apply ->local-date v))
  ([y m]
    (->local-date y m 1))
  ([y m d]
    (time/local-date y m d)))
