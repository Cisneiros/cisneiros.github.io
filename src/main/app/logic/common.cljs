(ns app.logic.common)

(def branded-icons
  #{:twitter :github :linkedin-in :speaker-deck})

(defn icon-base-class [title]
  (if (branded-icons title)
    "fab"
    "fal"))
