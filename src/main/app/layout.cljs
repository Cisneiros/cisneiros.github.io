(ns app.layout
  (:require [garden.units :refer [px percent]]))

(def color {:primary           "#CA054D"
            :secondary         "#0E79B2"
            :foreground        "#0F0A0A"
            :background        "#FEFFFE"
            :background-accent "#E3E4DB"})

(def media-query
  {:medium {:screen :only :min-width (px 481)}
   :large  {:screen :only :min-width (px 980)}})
