(ns app.layout-test
  (:require [cljs.test :refer [deftest are]]
            [app.layout :as layout]))

(deftest color
  (are [k] (some #{k} (keys layout/color))
       :primary
       :secondary
       :foreground
       :background
       :background-accent))

(deftest media-query
  (are [k] (map? (k layout/media-query))
       :medium
       :large))
