(ns app.logic.time-test
  (:require [cljs.test :refer [deftest is are]]
            [app.logic.time :as logic.time]))

(def events
  {:xmas {:date (cljs-time.core/local-date 2019 12 25)}
   :new-year {:date (cljs-time.core/local-date 2020 1 1)}
   :halloween {:date (cljs-time.core/local-date 2019 10 31)}})

(def local-date (cljs-time.core/local-date 2019 9 1))

(deftest reverse-sort-by-date
  (is (= (logic.time/reverse-sort-by-date events)
         [[:new-year (:new-year events)]
          [:xmas (:xmas events)]
          [:halloween (:halloween events)]]))

  (is (= (logic.time/reverse-sort-by-date {})
         []))

  (is (= (logic.time/reverse-sort-by-date nil)
         [])))

(deftest ->year-month
  (is (= (logic.time/->year-month local-date)
         "Sep 2019")))

(deftest ->month-name
  (is (= (logic.time/->month-name local-date)
         "Sep")))

(deftest ->year
  (is (= (logic.time/->year local-date)
         2019)))

(deftest ->day
  (is (= (logic.time/->day local-date)
         1)))

(deftest ->local-date
  (are [v local-date] (cljs-time.core/= (apply logic.time/->local-date v)
                                        local-date)
       [2019 12 25] (cljs-time.core/local-date 2019 12 25)
       [2019 12] (cljs-time.core/local-date 2019 12 1)))
