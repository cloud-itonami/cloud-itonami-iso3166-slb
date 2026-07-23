(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Solomon Islands market-entry law, whether a claimed
  engagement fee actually equals base + months x rate, whether the
  engagement's own declared investment activity actually falls within
  the specific activity or activities named on its own Foreign
  Investment Act 2005 s.21(2)(b) Certificate of Registration, whether
  an Inland Revenue Division (IRD) TIN record has been verified for a
  filing that requires it, or when a draft stops being a draft and
  becomes a real-world solomonbusinessregistry.gov.sb submission, so
  this MUST be a separate system able to *reject* a proposal and fall
  back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Six checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. Certificate scope
       exceeded                    -- for `:filing/submit`, when the
                                       engagement declares
                                       `:claims-certificate-covers-
                                       activity? true` (i.e. it is
                                       relying on an EXISTING
                                       Certificate of Registration to
                                       authorise the investment
                                       activity it is now conducting,
                                       per the Foreign Investment Act
                                       2005's own s.12(3)-(4)),
                                       INDEPENDENTLY recompute whether
                                       the engagement's own declared
                                       `:declared-activity` is actually
                                       a member of the SET of
                                       activities specified on its own
                                       `:certificate-registered-
                                       activities` (the certificate's
                                       own s.21(2)(b) activity list),
                                       and HARD-hold if not. FLAGSHIP
                                       check for this vertical -- a
                                       PER-ENTITY DYNAMIC-SCOPE-LIST
                                       CONTAINMENT check (the reference
                                       set is itself per-engagement
                                       ground truth, not a constant
                                       this catalog bakes in),
                                       stopping a filing from evading
                                       the Registrar of Foreign
                                       Investment's own registration
                                       process by claiming an
                                       activity is already covered by
                                       authority it was never granted.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. IRD TIN record unverified    -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-tin-record? true`,
                                       INDEPENDENTLY check `:tin-
                                       record-verified?`. CONDITIONAL
                                       on the engagement's own ground
                                       truth. Grounded in the Inland
                                       Revenue Division (IRD)'s own
                                       tax-information page (see
                                       `marketentry.facts`): \"The legal
                                       entity that operates the
                                       business will need a Tax
                                       Identification Number-TIN.\"
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real portal package and submitting a real portal
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(Company Haus登録/IRD TIN登録/Registrar of Foreign Investment証明書/証明書適用範囲確認/代理人確認等)が充足していない状態での提案"}]))))

(defn- certificate-scope-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own declared investment activity is actually a member of
  its own Certificate of Registration's own declared activity set --
  the flagship check this vertical adds. HARD-hold when the engagement
  declares `:claims-certificate-covers-activity? true` but is not
  independently covered."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (registry/certificate-scope-exceeded-claim? e)
        [{:rule :certificate-scope-exceeded
          :detail (str subject " はForeign Investment Act 2005 s.12(3)-(4)/s.21(2)(b)に基づき"
                      "既存Certificate of Registrationが当該investment activityを適用範囲に含むと宣言しているが、"
                      "独立再計算(申告activity vs 証明書自体に記載されたactivities一覧)により対象activityが証明書の適用範囲に含まれない"
                      "(Registrar of Foreign Investmentへの新規/追加登録が必要)")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- tin-record-unverified-violations
  "For `:filing/submit`, when the engagement declares `:requires-tin-
  record? true`, INDEPENDENTLY check `:tin-record-verified?` --
  CONDITIONAL on the engagement's own ground truth. Grounded in the
  Inland Revenue Division (IRD)'s own tax-information page."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-tin-record? e))
                 (not (true? (:tin-record-verified? e))))
        [{:rule :tin-record-unverified
          :detail (str subject " はInland Revenue Division(IRD) TIN登録記録の確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (certificate-scope-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (tin-record-unverified-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
