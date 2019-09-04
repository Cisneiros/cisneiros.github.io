(ns app.logic.event)

(defn role-icon [role]
  (case role
    :speaker :presentation
    :interviewer :comments
    :instructor :chalkboard-teacher
    :organizer :bullhorn
    :presenter :microphone-alt
    :track-coordinator :tasks-alt
    :staff :id-badge
    :badge))

(defn role-label [role]
  (case role
    :speaker "Speaker"
    :interviewer "Interviewer"
    :instructor "Instructor"
    :organizer "Organizer"
    :presenter "Presenter"
    :track-coordinator [:<> "Track" [:br] " coordinator"]
    :staff "Staff"
    (name role)))
