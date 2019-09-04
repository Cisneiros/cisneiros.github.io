(ns app.build)

(defn- print-id [id]
  (print (str "[" id "] ")))

(defn copy-assets!
  {:shadow.build/stage :flush}
  [build-state]
  (let [id (:shadow.build/build-id build-state)
        output-dir (-> build-state
                       :shadow.build/config
                       :output-dir)]
    (print-id id)
    (println "Copying assets to output-dir" output-dir)

    (print-id id)
    (clojure.pprint/pprint
      (clojure.java.shell/sh "cp" "-a" "resources/assets/." output-dir)))
  build-state)
