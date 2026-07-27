(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `certificate-covers-activity?` / `certificate-scope-exceeded-claim?`
  are the SAME discipline applied to a genuinely Solomon-Islands-
  specific mechanism: the Foreign Investment Act 2005's own ss.12(3)-
  (4)/13(a)/21(2)(b) certificate-of-registration ACTIVITY-SCOPE regime
  -- a foreign investor's certificate of registration authorises ONLY
  the specific investment activity or activities named on that
  certificate (s.21(2)(b)); conducting an investment activity the
  certificate does not name is, on the Act's own text, unauthorised
  (s.12(3)(a)) even though the investor holds A certificate. A filing
  that claims an existing certificate already covers the activity it is
  now conducting, while its own declared activity is not actually among
  that certificate's own declared activity set, is trying to rely on
  authority it was never granted -- exactly the kind of ground-truth
  mismatch this governor independently recomputes and HARD-holds.

  This is honestly a PER-ENTITY DYNAMIC-SCOPE-LIST CONTAINMENT check
  shape -- genuinely different from every prior sibling this iteration
  surveyed (see `marketentry.facts` docstring for the comparative
  survey): it is not a single value compared against a fixed number
  (FJI/VUT), not a cross-record aggregation (LKA/NRU), and not
  membership of a FIXED, catalog-wide list (BTN's own Negative List);
  the reference set here is itself per-engagement ground-truth data
  (the SAME certificate's own declared activities), not a constant this
  catalog bakes in.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real registration portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(def ^:private money-scale
  "Sub-minor-unit scale used when comparing two money amounts: 1/10000 of
  a unit. Coarser than double representation error by many orders of
  magnitude, finer than any real currency's minor unit (2 decimals for
  most, 3 for KWD/BHD/OMR, 0 for JPY/KRW)."
  10000)

(defn- money=
  "Exact-at-money-precision equality for two amounts.

  `==` on raw doubles is NOT the right comparison for money. With
  whole-unit fees the two agree, but as soon as an amount carries
  cents the sum `base + rate x months` is routinely not the double
  nearest the true total, and a CORRECT claim compares false: measured
  on this exact shape, 40,989 of 327,060 cent-denominated combinations
  (12.5%) were rejected while being right, against 0 of 327,060 in
  whole units.

  Rounding both sides to `money-scale` before comparing removes the
  representation error while preserving every distinction money can
  actually carry."
  [x y]
  (and (number? x) (number? y)
       (= (Math/round (* money-scale (double x)))
          (Math/round (* money-scale (double y))))))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  ;; nil when any field is not a number: an un-recomputable engagement is
  ;; un-verifiable, which is neither `correct` nor a ClassCastException
  ;; thrown out of the caller.
  (when (and (number? base-fee) (number? monthly-rate) (number? monitoring-months))
    (+ (double base-fee)
       (* (double monthly-rate) (double monitoring-months)))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (money= claimed-fee (compute-engagement-fee engagement)))

(defn certificate-covers-activity?
  "Ground truth: is `engagement`'s own declared investment activity
  (`:declared-activity`) actually a member of the SET of investment
  activities specified on its own Certificate of Registration
  (`:certificate-registered-activities`), per the Foreign Investment
  Act 2005 s.21(2)(b)? A missing declared activity or a missing/empty
  certificate activity set simply fails (does not throw) -- an
  engagement with nothing on record is not covered by anything."
  [{:keys [declared-activity certificate-registered-activities]}]
  (boolean
   (and (some? declared-activity)
        (seq certificate-registered-activities)
        (contains? (set certificate-registered-activities) declared-activity))))

(defn certificate-scope-exceeded-claim?
  "Does `engagement` declare `:claims-certificate-covers-activity? true`
  (i.e. it is relying on an EXISTING Certificate of Registration to
  authorise the investment activity it is now conducting, per s.12(3)-
  (4)) while the INDEPENDENTLY recomputed `certificate-covers-activity?`
  is false (its own declared activity is not actually among the
  specific activities listed on its own certificate, per s.21(2)(b))?
  An engagement that does NOT claim existing-certificate coverage is
  never flagged by this check (entity/engagement-scope-gated, the same
  discipline FJI's `:claims-simplified-procurement?`-gated check and
  PNG's `:foreign-ownership-pct`-gated check use) -- e.g. a filing that
  is itself the FIRST application for a certificate has no s.12(3)-(4)
  conformance obligation yet."
  [{:keys [claims-certificate-covers-activity?] :as engagement}]
  (boolean (and claims-certificate-covers-activity?
                (not (certificate-covers-activity? engagement)))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real registration
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
