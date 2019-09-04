(ns app.logic.profile)

(defn contact-icon [id]
  (case id
    :email :envelope
    :linkedin :linkedin-in
    id))

(defn contact-label [id]
  (case id
    :linkedin "LinkedIn"
    :github "Github"
    :twitter "Twitter"
    :speaker-deck "Speaker Deck"
    :email "E-mail"
    (name id)))
