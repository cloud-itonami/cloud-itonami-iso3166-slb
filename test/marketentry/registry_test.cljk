(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "SLB" 0)
        s (registry/register-submit "eng-1" "SLB" 0)]
    (is (= "SLB-DFT-000000" (get d "draft_number")))
    (is (= "SLB-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "SLB" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest certificate-covers-activity-when-declared-activity-is-listed
  (testing "the declared activity is a member of the certificate's own registered-activity set -> covered"
    (is (true? (registry/certificate-covers-activity?
                {:declared-activity "retail trading"
                 :certificate-registered-activities #{"retail trading" "wholesale trading"}})))
    (is (true? (registry/certificate-covers-activity?
                {:declared-activity "wholesale trading"
                 :certificate-registered-activities #{"retail trading" "wholesale trading"}})))))

(deftest certificate-does-not-cover-activity-when-not-listed
  (testing "the declared activity is NOT a member of the certificate's own registered-activity set -> not covered"
    (is (false? (registry/certificate-covers-activity?
                 {:declared-activity "tourism accommodation development"
                  :certificate-registered-activities #{"retail trading" "wholesale trading"}}))))
  (testing "no declared activity or no certificate activities at all -> not covered (fails, does not throw)"
    (is (false? (registry/certificate-covers-activity? {})))
    (is (false? (registry/certificate-covers-activity?
                 {:declared-activity "retail trading" :certificate-registered-activities nil})))
    (is (false? (registry/certificate-covers-activity?
                 {:declared-activity "retail trading" :certificate-registered-activities #{}})))))

(deftest certificate-scope-exceeded-claim-is-entity-scope-gated
  (testing "an engagement NOT claiming existing-certificate coverage is never flagged, even if its activity would fail coverage"
    (is (false? (registry/certificate-scope-exceeded-claim?
                 {:claims-certificate-covers-activity? false
                  :declared-activity "tourism accommodation development"
                  :certificate-registered-activities #{"retail trading"}}))))
  (testing "an engagement claiming existing-certificate coverage whose declared activity is NOT on the certificate -> exceeded claim"
    (is (true? (registry/certificate-scope-exceeded-claim?
                {:claims-certificate-covers-activity? true
                 :declared-activity "tourism accommodation development"
                 :certificate-registered-activities #{"retail trading" "wholesale trading"}}))))
  (testing "an engagement claiming existing-certificate coverage whose declared activity IS on the certificate -> not flagged"
    (is (false? (registry/certificate-scope-exceeded-claim?
                 {:claims-certificate-covers-activity? true
                  :declared-activity "retail trading"
                  :certificate-registered-activities #{"retail trading" "wholesale trading"}})))))

;; ---------------------------------------------------------------------------
;; Money is compared at money precision, not at double precision
;; ---------------------------------------------------------------------------

(deftest whole-unit-fees-were-already-correct-and-stay-correct
  (testing "the seeded shape: base + rate x months in whole currency units"
    (is (registry/engagement-fee-matches-claim?
         {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
           :claimed-fee 860000.0}))))

(deftest cent-denominated-fees-are-no-longer-rejected-while-correct
  (testing "`(== (double claimed) (+ (double base) (* (double rate) (double months))))`
            rejected CORRECT totals once an amount carried cents -- 40,989 of
            327,060 combinations (12.5%), against 0 of 327,060 in whole units"
    (let [bad (for [m (range 1 37)
                    bc (range 10000 90000 2100)
                    rc (range 500 6000 210)
                    :let [truth (/ (+ bc (* rc m)) 100.0)]
                    :when (not (registry/engagement-fee-matches-claim?
                                {:base-fee (/ bc 100.0) :monthly-rate (/ rc 100.0)
                                  :monitoring-months m :claimed-fee truth}))]
                [m (/ bc 100.0) (/ rc 100.0) truth])]
      (is (empty? bad) (str "false rejections: " (count bad) " e.g. " (first bad))))))

(deftest a-genuinely-wrong-fee-is-still-caught
  (testing "rounding to money precision must not blunt the check"
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 860000.01})))
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 859999.99})))))

(deftest an-unverifiable-fee-never-matches
  (testing "un-verifiable is not the same as correct, and not a crash"
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12})))
    (is (not (registry/engagement-fee-matches-claim?
              {:base-fee "500000" :monthly-rate 30000 :monitoring-months 12
                :claimed-fee 860000.0})))
    (is (nil? (registry/compute-engagement-fee {:base-fee 500000 :monthly-rate 30000})))))
