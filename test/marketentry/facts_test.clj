(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest slb-has-spec-basis
  (let [sb (facts/spec-basis "SLB")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-registry-spec-basis "SLB")))
    (is (some? (facts/corporate-number-spec-basis "SLB")))
    (is (some? (facts/foreign-investment-spec-basis "SLB")))
    (is (some? (facts/certificate-scope-spec-basis "SLB")))))

(deftest slb-rep-spec-basis-is-honestly-absent
  (testing "no dedicated local-representative/agent section number was confirmed for SLB this session -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "SLB")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "SLB")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "SLB" all)))
    (is (not (facts/required-evidence-satisfied? "SLB" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SLB" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest certificate-scope-spec-basis-criteria
  (let [cs (facts/certificate-scope-spec-basis "SLB")]
    (is (true? (get-in cs [:certificate-scope-criteria :declared-activity-must-be-listed-on-own-certificate?])))))
