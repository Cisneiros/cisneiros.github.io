(ns app.core
  (:require [reagent.core :as r]
            [app.components.app :as components.app]
            [app.global-styling :as global-styling]
            [app.io.config :as io.config]))

(def world (r/atom io.config/initial-config))

(defn main-component []
  [components.app/app @world])

(defn reload! []
  (reset! world (io.config/read-all-config!))

  (global-styling/reset-css!)
  (global-styling/apply-global-style!)

  (r/render [main-component]
            (.querySelector js/document "#app"))
  (println "Code updated."))

(defn main! []
  (println "App loaded!")
  (reload!))
