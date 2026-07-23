(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest slb-has-spec-basis
  (let [sb (facts/spec-basis "SLB")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SLB" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["slb.companies-act-2009"]
         (mapv :statute/id (facts/by-topic "SLB" :corporate-governance))))
  (is (= ["slb.labour-act-cap73"]
         (mapv :statute/id (facts/by-topic "SLB" :labor))))
  (is (empty? (facts/by-topic "SLB" :data-protection))
      "no data-protection statute independently confirmed this iteration -- honestly absent, see namespace docstring")
  (is (empty? (facts/by-topic "ATL" :labor))))
