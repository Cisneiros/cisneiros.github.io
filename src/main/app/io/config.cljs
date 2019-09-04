(ns app.io.config
  (:require [app.macros :include-macros true :as macros]
            [app.logic.time :as logic.time]))

(def ^:private readers {'date       logic.time/->local-date
                        'year-month logic.time/->local-date})

(defn read-config [config]
  (cljs.reader/read-string
    {:readers readers}
    config))

(defn read-profile! []
  (-> "resources/profile.edn"
      macros/slurp
      read-config))

(defn read-events! []
  (-> "resources/events.edn"
      macros/slurp
      read-config))

(defn read-jobs! []
  (-> "resources/jobs.edn"
      macros/slurp
      read-config))

(def initial-config
  {:jobs    {}
   :events  {}
   :profile {}})

(defn read-all-config! []
  {:jobs    (read-jobs!)
   :events  (read-events!)
   :profile (read-profile!)})
