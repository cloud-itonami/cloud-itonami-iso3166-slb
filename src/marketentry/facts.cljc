(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Solomon Islands' real market-entry surface (curl/WebFetch-verified
  2026-07-22/23; where a live official page could not be reached this
  session, that is stated explicitly and the corresponding fact is
  sourced from a `web.archive.org` snapshot of the SAME official
  document instead -- never invented):

  - **Sourcing discipline for this iteration**: this session's WebSearch
    budget was already exhausted before this task began (the same
    constraint several sibling catalogs in this family record), so
    discovery used direct navigation from `gov.sb`-adjacent domains
    (`ird.gov.sb`, `commerce.gov.sb`, `solomonbusinessregistry.gov.sb`,
    `investsolomons.gov.sb`) plus PacLII's Solomon Islands legislation
    database. **Every direct fetch of live `paclii.org` (both
    `sb/legis/consol_act/` and `sb/legis/num_act/`) returned a
    Cloudflare \"Just a moment...\" bot-detection challenge
    (`cf-mitigated: challenge` response header, confirmed via `curl -sI`)
    -- this iteration did NOT attempt to bypass it** (hard rule); every
    PacLII citation below is instead read from a `web.archive.org`
    snapshot of the exact same official PacLII page, fetched and read
    directly this session. `moft.gov.sb`/`finance.gov.sb`/
    `treasury.gov.sb` failed DNS resolution and `mof.gov.sb` timed out --
    no live Ministry of Finance and Treasury (MOFT) site could be
    reached this session; `solomons.gov.sb` returned only a bare HTTP
    307 redirect this iteration did not follow, so it is not cited for
    any specific claim below.
  - **Public procurement** is governed by the **Public Financial
    Management Act 2013 (No. 9 of 2013)** -- this iteration fetched and
    read the Act's own full consolidated text via a `web.archive.org`
    snapshot of PacLII (`sb/legis/num_act/pfma2013221/`, snapshot
    `20250129141304`). Own title page: \"SOLOMON ISLANDS PUBLIC FINANCIAL
    MANAGEMENT ACT 2013 (NO. 9 OF 2013) ... PASSED by the National
    Parliament this twelveth day of September 2013 ... ASSENTED to ...
    this 10th day of October 2013 ... AN ACT TO PROVIDE A FRAMEWORK FOR
    EXERCISING SOUND PUBLIC FINANCIAL MANAGEMENT BY THE GOVERNMENT OF
    SOLOMON ISLANDS ... TO REPEAL CERTAIN PARTS OF THE PUBLIC FINANCE AND
    AUDIT ACT (Cap. 120)\".
    - Own PART 9 -- PROCUREMENT AND USE OF PUBLIC RESOURCES (ss.72-75):
      s.72(1) \"The Minister of Finance shall prepare rules and
      regulations for procurement that are consistent with the
      principles of this Act and internationally accepted best
      practices\"; s.72(3) accountable officers \"shall comply with the
      prescribed procurement planning, tendering and contracting
      processes\"; s.73(1) every accountable/accounting officer shall
      conduct procurement \"in accordance with the prescribed
      procedures\"; s.73(2) lists conduct that undermines competitive
      purchasing as prohibited, including, own text, s.73(2)(d):
      \"apportion a procurement transaction into parts to avoid the
      procurement rules\"; s.74 lists the prescribed-procedure topics:
      publication of procurement notices, invitations to bid/bid
      submissions/minimum bidding periods/standard bidding documents,
      bid opening and evaluation, publication of the bid award. **This
      iteration did NOT find or fetch the delegated procurement
      Regulations themselves** (s.72(1)'s own \"rules and regulations\"
      -- no live Ministry of Finance and Treasury site could be reached
      this session to look for them, and PacLII's own num_act index
      lists no separate \"Public Financial Management (Procurement)
      Regulations\" entry this iteration could find) -- so, honestly,
      **no specific procurement-value dollar threshold is modelled for
      Solomon Islands** (unlike FJI's reg 29/reg 30 or VUT's s.3(2)/(4),
      both of which read a threshold directly off the Act's OWN text).
      This is the same honest scope-narrowing discipline this family's
      other catalogs use for a delegated, unread number (e.g. CAF's
      unread ministerial arrêté).
    - Own s.73(2)(d)'s anti-fragmentation rule (\"apportion a procurement
      transaction into parts to avoid the procurement rules\") is a real,
      own-text finding this iteration deliberately did NOT build into a
      cross-record aggregation governor check -- `cloud-itonami-iso3166-
      lka` (Sri Lanka, Procurement Guidelines - 2024 s.4.3 slice
      reconciliation) and `cloud-itonami-iso3166-nru` (Nauru,
      cross-entity aggregate anti-structuring) already occupy that check
      SHAPE in this family; reusing it here would not be a genuinely
      different shape. Solomon Islands' flagship check (below) instead
      grounds in the Foreign Investment Act 2005's own certificate-scope
      mechanism, a shape not yet used by any sibling this iteration
      surveyed (grep across ~179 sibling `governor.cljc` files fetched
      this session, see repo README for the survey method).
  - **Business/company registration**: **Company Haus**, the Registry of
    Companies division within the **Ministry of Commerce, Industries,
    Labour and Immigration (MCILI)**, confirmed directly from
    `commerce.gov.sb/company-haus/` (fetched directly this session,
    live, HTTP 200). Its own text, quoted directly: \"Company Haus is
    the Registry of Companies... [it] handles registration of
    businesses, companies, secured transactions and charitable
    organisations\", with three core functions: \"1. Registration of
    companies, businesses and charitable organisations. 2.
    Administration and enforcement of the Company's Act. 3. Registration
    of secured transactions.\" Company Haus's own page names the
    administered Act only as \"the Company's Act\", with no year given on
    that specific page -- the exact title and year (**Companies Act
    2009, No. 1 of 2009**) is independently confirmed from TWO further
    sources fetched this session: (a) `solomonbusinessregistry.gov.sb/
    companies/legislation-companies/` (fetched directly, live, HTTP 200),
    whose own list reads \"Companies Act 2009\" and \"Solomon Islands
    Companies (Insolvency and Receivership) Act 2009\"; (b) a
    `web.archive.org` snapshot of PacLII's own numbered-Act index
    (`sb/legis/num_act/toc-C.html`, snapshot `20251011062548`), whose own
    entry reads \"Companies Act 2009\", and the Act's own full text (a
    further `web.archive.org` snapshot, `sb/legis/num_act/ca2009107/`,
    snapshot `20250129141850`) states on its own title page: \"SOLOMON
    ISLANDS COMPANIES ACT 2009 (No. 1 of 2009) ... AN ACT TO PROVIDE FOR
    THE FORMATION AND GOVERNANCE OF PRIVATE, PUBLIC AND COMMUNITY
    COMPANIES, AND TO REPEAL THE COMPANIES ACT (CAP. 175)\". The Act's
    own s.170 (Part 13, Division 1): \"This section establishes the
    Registrar of Companies who shall be a legally qualified person and
    shall be appointed pursuant to section 118 of the Constitution.\"
    - `solomonbusinessregistry.gov.sb` (fetched directly this session,
      live, HTTP 200) is the live electronic register: its own homepage
      states \"an electronic register available to the public 24 hours a
      day, 7 days a week\", with four service areas -- Companies,
      Business Names, Foreign Investors, and About Solomon Islands.
    - A separate regime governs business (sole-trader/partnership) NAMES
      as distinct from companies: the **Business Names Act 2014 (No. 13
      of 2014)**, confirmed from `solomonbusinessregistry.gov.sb/
      business-names/how-to-register-a-business-name/learn-what-a-
      business-name-is/` (fetched directly, live) and independently from
      PacLII's own numbered-Act index/full text (`web.archive.org`
      snapshots as above): own title page \"SOLOMON ISLANDS BUSINESS
      NAMES ACT 2014 (NO. 13 OF 2014) ... AN ACT TO PROVIDE FOR THE
      REGISTRATION AND USE OF BUSINESS NAMES, FOR THE REPEAL OF THE
      REGISTRATION OF THE BUSINESS NAMES ACT (CAP. 178)\".
  - **Foreign investment**: the **Foreign Investment Act 2005 (No. 7 of
      2005)**, administered by the **Registrar of Foreign Investment**
      (InvestSolomons / the Foreign Investment Division (FID) of MCILI).
      This iteration fetched and read the Act's own full text via a
      `web.archive.org` snapshot of PacLII (`sb/legis/num_act/
      fia2005219/`, snapshot `20250129142313`). Own title page: \"LAWS OF
      SOLOMON ISLANDS THE FOREIGN INVESTMENT ACT 2005 (NO.7 OF 2005) ...
      Passed by the National Parliament this fourteenth day of November,
      2005 ... Assented to ... this sixteenth day of December 2005.\"
      `commerce.gov.sb/foreign-investment/` (fetched directly this
      session, live, HTTP 200) independently corroborates the year and
      names the office and director: \"InvestSolomons (Foreign Investment
      Division)\", director Lynnette Dawheya, own text: \"To ensure that
      all foreign investors operating in Solomon Islands are registered
      and adhere to their terms of registration as required by the
      foreign investment act 2005 and subsidiary legislations.\"
      `investsolomons.gov.sb` (fetched directly this session, live)
      independently corroborates: \"Invest Solomons (Foreign Investment
      Division) is the government division dedicated to promoting,
      facilitating, registering and monitoring foreign investment in
      Solomon Islands\", under \"the Ministry of Commerce, Industry,
      Labour and Immigration (MCILI)\", with a \"Reserved List\" legal
      notice linked under its own Legal Notices section.
    - **Honest discrepancy disclosed, not silently resolved**:
      `solomonbusinessregistry.gov.sb/companies/legislation-companies/`
      (fetched directly this session, live) lists the SAME Act as
      \"Foreign Investment Act 2006\" (one year off). This iteration
      treats **2005** as the correct year -- it is independently
      confirmed by TWO sources: PacLII's own numbered-Act index/full
      primary-text title page (a government legislation database, not a
      ministry's own summary page) AND `commerce.gov.sb`'s own quoted
      \"foreign investment act 2005\" text -- while \"2006\" appears on
      only the one business-registry summary page. Both years are
      reported here rather than silently picking one.
    - The Act's own ss.3-5 (Part 2 -- Administration): s.3 \"There is a
      Registrar of Foreign Investment... The person from time to time
      holding or acting in the position of Director of the Investment
      Division of the Department responsible under the Minister for
      administering this Act is the Registrar\"; s.4(1) functions include
      reviewing the reserved list, receiving/processing registration
      applications, issuing certificates of registration, and monitoring
      compliance.
    - Own s.9 \"Reserved list\": \"There is a reserved list of investment
      activities... set out in the regulations\", gated on citizen
      participation and at-least-10-business-operations criteria (s.9(3))
      -- this iteration did NOT independently fetch the reserved-list
      Regulations' own text (only the Act's own enabling s.9, plus
      InvestSolomons' own site confirming a \"Reserved List\" legal
      notice exists), so, honestly, **no specific reserved-activity list
      is modelled** here (the same scope limit VUT's own catalog
      discloses for its own Foreign Investment Act, 2019).
    - Own ss.11-14 (Division 1 -- General matters) and s.21 are this
      vertical's FLAGSHIP-check foundation (see below): s.11 \"The
      purpose of registering an investment activity is to (a) ensure
      that foreign investors do not conduct reserved activities or
      prohibited activities; and (b) facilitate the monitoring...\"; s.12
      (2)(b): registration is \"the first requirement... which a foreign
      investor who intends... to conduct an investment activity in
      Solomon Islands shall comply with\"; s.12(3): \"Unless a foreign
      investor holds a certificate of registration for an investment
      activity or investment activities he conducts or intends to
      conduct, the foreign investor shall not... (a) conduct the
      investment activity...\"; s.13(a): registration \"does not operate
      to... relieve a foreign investor conducting the investment
      activity from complying with any other law of Solomon Islands\";
      s.14(1)-(2): \"A certificate of registration may be issued for more
      than one investment activity... A foreign investor may hold more
      than one certificate of registration at any one time.\" Own s.21
      (\"Form and content of certificate of registration\"), s.21(2)(b):
      the certificate shall \"specify the investment activity or
      investment activities for which the certificate is issued\" -- i.e.
      each certificate's authority is SCOPED to the specific activity or
      activities named on it, own-text confirmed, not a blanket
      authorization to conduct any/all investment activity.
  - **Tax/TIN registration**: the **Inland Revenue Division (IRD)**,
      confirmed directly from `www.ird.gov.sb` (fetched directly this
      session, live, HTTP 200) and its own `tax-information/` and
      `business-records/` pages (both fetched directly, live). The
      `tax-information/` page's own text: \"The legal entity that
      operates the business will need a Tax Identification Number-TIN\",
      naming the registration forms \"TAA 1\" (individuals) and \"TAA 1A\"
      (non-individuals), and citing (own text) the **Income Tax Act**
      (\"Cap 123\" referenced in one of its own links) and the **Goods Tax
      Act** -- neither a Sales Tax Act nor a Tax Administration Act is
      named on this specific page. This iteration did NOT independently
      fetch the Income Tax Act's own primary statutory text, only IRD's
      own citation of its title and chapter number; the FIA 2005's own
      s.43(1) (savings provision, read in the same PacLII snapshot as
      above) independently corroborates the chapter number in passing:
      \"...entitled to receive incentives under the Income Tax Act
      (Cap 123), the Customs and Excise Act (Cap 121)...\" -- an
      independent, unrelated official source naming the SAME chapter
      number. Online TIN registration/filing runs through IRD's own
      **E-Tax** system (`etax.ird.gov.sb`, linked from `ird.gov.sb` but
      not independently fetched this session).
  - `certificate-scope-spec-basis` grounds this vertical's FLAGSHIP check
      (see `marketentry.governor` / `marketentry.registry`) -- the
      Foreign Investment Act 2005's own ss.12(3)-(4)/13(a)/21(2)(b)
      certificate-of-registration ACTIVITY-SCOPE mechanism: a foreign
      investor's certificate of registration authorises ONLY the
      specific investment activity or activities named on that
      certificate (s.21(2)(b)), and conducting an investment activity a
      certificate does not name is, on the Act's own text, unauthorised
      (s.12(3)(a)) even though the investor DOES hold a certificate of
      registration for something. This is honestly a PER-ENTITY
      DYNAMIC-SCOPE-LIST CONTAINMENT check -- a genuinely different
      SHAPE from every prior sibling this iteration surveyed (flat-value
      threshold: FJI/VUT; cross-record aggregation/anti-splitting:
      LKA/NRU; fixed global negative/reserved-list set-membership: BTN;
      ownership-percentage threshold: PNG; ordered tier-classification:
      BWA/WSM/MMR/CPV; paid-in-capital or bank-balance floor: GNQ/LBY;
      date/precedence/lookback: BRB/BRN/SYC; quorum: ERI; white-list
      claimed-benefit: GEO; statutory ceiling: COG). It does not compare
      a single declared value against a fixed number or a fixed list --
      it checks whether a filing's own declared activity is a member of
      THAT SAME FILING'S OWN certificate's own declared activity set,
      which is itself data the engagement/certificate carries, not a
      constant baked into this catalog.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. SLB's
  base `:owner-authority`/`:legal-basis` ground the Public Financial
  Management Act 2013's own procurement Part (ss.72-75) -- honestly with
  NO specific dollar threshold modelled (the delegated Regulations were
  not found/fetched this session, see namespace docstring).
  `:corporate-registry-*` grounds Company Haus / the Companies Act 2009 +
  Business Names Act 2014. `:corporate-number-*` grounds the Inland
  Revenue Division / Income Tax Act (Cap 123) TIN regime.
  `:foreign-investment-*` grounds the Registrar of Foreign Investment /
  Foreign Investment Act 2005 citation. `:certificate-scope-owner-
  authority` / `:certificate-scope-legal-basis` / `:certificate-scope-
  criteria` / `:certificate-scope-provenance` ground this vertical's
  flagship governor check (`certificate-covers-activity?`/
  `certificate-scope-exceeded-claim?` in `marketentry.registry`)."
  {"SLB" {:name "Solomon Islands"
          :owner-authority "Ministry of Finance and Treasury (MOFT), per the Public Financial Management Act 2013's own s.72(1) (\"The Minister of Finance shall prepare rules and regulations for procurement\"); no live MOFT website (moft.gov.sb / finance.gov.sb / treasury.gov.sb all failed DNS resolution, mof.gov.sb timed out) could be reached this session -- this citation rests on the Act's own text alone, read via a web.archive.org snapshot of PacLII (live paclii.org returned a Cloudflare bot-detection challenge this iteration did not bypass)"
          :legal-basis "Public Financial Management Act 2013 (No. 9 of 2013), own PART 9 -- PROCUREMENT AND USE OF PUBLIC RESOURCES: s.72 (Minister of Finance to prescribe procurement rules/regulations; accountable officers to plan/prioritise procurement and comply with prescribed processes), s.73 (procurement responsibilities; s.73(2)(d) own text prohibits apportioning \"a procurement transaction into parts to avoid the procurement rules\"), s.74 (prescribed procedures: notices, bid invitations/submissions/minimum bidding periods/standard bidding documents, bid opening/evaluation, publication of award), s.75 (use of public resources). This iteration did NOT find or fetch the delegated procurement Regulations themselves (s.72(1)'s own \"rules and regulations\"), so no specific procurement-value dollar threshold is modelled -- an honest scope limit, not an invented number"
          :national-spec "No independent, bespoke government-built e-procurement self-service portal or live Ministry of Finance and Treasury site could be confirmed reachable this session. Market-entry registration itself (companies, business names, foreign investors) runs through the live solomonbusinessregistry.gov.sb electronic register, own text: \"an electronic register available to the public 24 hours a day, 7 days a week\", with four service areas: Companies, Business Names, Foreign Investors, About Solomon Islands"
          :provenance "https://web.archive.org/web/20250129141304/http://www.paclii.org/sb/legis/num_act/pfma2013221/ ; https://www.solomonbusinessregistry.gov.sb/"
          :required-evidence ["Company Haus (Registrar of Companies, MCILI) company registration record (Companies Act 2009, No. 1 of 2009, s.170) or Business Names Act 2014 (No. 13 of 2014) business-name registration record, as applicable to the entity type"
                              "Inland Revenue Division (IRD) TIN registration record (Income Tax Act, Cap 123; TAA 1 / TAA 1A via E-Tax, etax.ird.gov.sb)"
                              "Registrar of Foreign Investment (InvestSolomons / Foreign Investment Division, MCILI) Certificate of Registration record (Foreign Investment Act 2005, No. 7 of 2005, ss.3-5, 12, 21), when the engagement is a foreign investor"
                              "Certificate-of-registration activity-scope conformance record, when the engagement declares reliance on an existing certificate for the investment activity it is now conducting"
                              "Authorized-representative confirmation record"]
          :corporate-registry-owner-authority "Company Haus, the Registry of Companies division of the Ministry of Commerce, Industries, Labour and Immigration (MCILI); Registrar of Companies established by the Companies Act 2009 (No. 1 of 2009) s.170"
          :corporate-registry-legal-basis "Companies Act 2009 (No. 1 of 2009) (own title page, web.archive.org snapshot of PacLII: \"AN ACT TO PROVIDE FOR THE FORMATION AND GOVERNANCE OF PRIVATE, PUBLIC AND COMMUNITY COMPANIES, AND TO REPEAL THE COMPANIES ACT (CAP. 175)\"; own s.170, Part 13: \"This section establishes the Registrar of Companies who shall be a legally qualified person and shall be appointed pursuant to section 118 of the Constitution.\"), alongside the Business Names Act 2014 (No. 13 of 2014) for business (non-company) name registration -- own title page: \"AN ACT TO PROVIDE FOR THE REGISTRATION AND USE OF BUSINESS NAMES, FOR THE REPEAL OF THE REGISTRATION OF THE BUSINESS NAMES ACT (CAP. 178)\". Company Haus's own page (commerce.gov.sb/company-haus/, fetched directly, live) names its administered Act only as \"the Company's Act\" without year -- the year/number is independently confirmed from solomonbusinessregistry.gov.sb's own legislation page and PacLII's own numbered-Act index/full text"
          :corporate-registry-provenance "https://commerce.gov.sb/company-haus/ ; https://www.solomonbusinessregistry.gov.sb/companies/legislation-companies/ ; https://web.archive.org/web/20250129141850/http://www.paclii.org/sb/legis/num_act/ca2009107/"
          :corporate-number-owner-authority "Inland Revenue Division (IRD)"
          :corporate-number-legal-basis "IRD's own tax-information page (ird.gov.sb, fetched directly this session): \"The legal entity that operates the business will need a Tax Identification Number-TIN\" -- TIN registration via forms TAA 1 (individuals) / TAA 1A (non-individuals), online access through E-Tax (etax.ird.gov.sb, linked but not independently fetched this session). The page's own citations name the Income Tax Act (\"Cap 123\") and the Goods Tax Act, with no Sales Tax Act or Tax Administration Act named on that page. The Cap 123 chapter number is independently corroborated by the Foreign Investment Act 2005's own s.43(1) savings provision (a second, unrelated official source, read from the same PacLII snapshot as the Foreign Investment Act itself): \"...entitled to receive incentives under the Income Tax Act (Cap 123), the Customs and Excise Act (Cap 121)...\". This iteration did not independently fetch the Income Tax Act's own primary statutory text"
          :corporate-number-provenance "https://www.ird.gov.sb/tax-information/ ; https://www.ird.gov.sb/business-records/"
          :foreign-investment-owner-authority "Registrar of Foreign Investment (the Director of the Investment Division of MCILI, per Foreign Investment Act 2005 s.3(2)); InvestSolomons / Foreign Investment Division (FID) of the Ministry of Commerce, Industries, Labour and Immigration (MCILI)"
          :foreign-investment-legal-basis "Foreign Investment Act 2005 (No. 7 of 2005) (own title page, web.archive.org snapshot of PacLII: \"THE FOREIGN INVESTMENT ACT 2005 (NO.7 OF 2005) ... Passed by the National Parliament this fourteenth day of November, 2005\"; commerce.gov.sb's own foreign-investment page, fetched directly, live, independently corroborates: \"as required by the foreign investment act 2005 and subsidiary legislations\"). NOTE: solomonbusinessregistry.gov.sb's own legislation-companies page instead lists this same Act as \"Foreign Investment Act 2006\" -- an honest, disclosed one-year discrepancy between two official sources; this catalog follows 2005 because it is independently confirmed by BOTH PacLII's own primary-text title page AND commerce.gov.sb's own quoted text, while 2006 appears on only the one summary page. Own ss.3-5 establish the Registrar and its functions (register investment activities, issue certificates, monitor compliance, review the reserved list); own s.9 establishes a reserved list of investment activities \"set out in the regulations\" -- this iteration did not independently fetch the reserved-list Regulations' own text, so no specific reserved-activity list is modelled"
          :foreign-investment-provenance "https://commerce.gov.sb/foreign-investment/ ; https://www.investsolomons.gov.sb/ ; https://web.archive.org/web/20250129142313/http://www.paclii.org/sb/legis/num_act/fia2005219/"
          :certificate-scope-owner-authority "Registrar of Foreign Investment, per the Foreign Investment Act 2005's own ss.3-5"
          :certificate-scope-legal-basis "Foreign Investment Act 2005 (No. 7 of 2005), own s.12(3): \"Unless a foreign investor holds a certificate of registration for an investment activity or investment activities he conducts or intends to conduct, the foreign investor shall not ... conduct the investment activity\"; own s.13(a): registration \"does not operate to ... relieve a foreign investor conducting the investment activity from complying with any other law of Solomon Islands\"; own s.21(2)(b): the certificate of registration shall \"specify the investment activity or investment activities for which the certificate is issued\" -- read directly off the Act's own text (web.archive.org snapshot of PacLII), not a delegated/unread number"
          :certificate-scope-criteria {:declared-activity-must-be-listed-on-own-certificate? true}
          :certificate-scope-provenance "https://web.archive.org/web/20250129142313/http://www.paclii.org/sb/legis/num_act/fia2005219/"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-slb R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For SLB this is deliberately nil --
  this iteration could not confirm a specific local-representative/agent
  section number in the Companies Act 2009, the Business Names Act 2014
  or the Foreign Investment Act 2005 (their own primary texts were read
  for other provisions this session, but no dedicated local-agent/
  representative section was found) -- the same honest-scope-narrowing
  discipline FJI's/VUT's own catalogs use when a mechanism plausibly
  exists but its current, citable shape cannot be confirmed."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-registry-spec-basis
  "The jurisdiction's company/business registry regime, or nil. For SLB
  this is Company Haus (MCILI) under the Companies Act 2009 + Business
  Names Act 2014."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-registry-owner-authority sb)
      (select-keys sb [:corporate-registry-owner-authority
                       :corporate-registry-legal-basis
                       :corporate-registry-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number/tax-id regime, or nil. For SLB
  this is IRD's TIN regime under the Income Tax Act (Cap 123)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn foreign-investment-spec-basis
  "The jurisdiction's foreign-investment regime, or nil. For SLB this is
  the Registrar of Foreign Investment / Foreign Investment Act 2005
  citation (the base regime the flagship certificate-scope check is
  built on top of)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:foreign-investment-owner-authority sb)
      (select-keys sb [:foreign-investment-owner-authority
                       :foreign-investment-legal-basis
                       :foreign-investment-provenance]))))

(defn certificate-scope-spec-basis
  "The jurisdiction's certificate-of-registration activity-scope regime,
  or nil. For SLB this is real and current -- the flagship check this
  vertical adds is grounded here (Foreign Investment Act 2005, ss.12(3)-
  (4)/13(a)/21(2)(b))."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:certificate-scope-owner-authority sb)
      (select-keys sb [:certificate-scope-owner-authority
                       :certificate-scope-legal-basis
                       :certificate-scope-criteria
                       :certificate-scope-provenance]))))
