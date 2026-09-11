# cloud-itonami-iso3166-slb

**SLB**: Solomon Islands.

- public procurement
- Company Registry

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as every other `cloud-itonami-iso3166-*` sibling:

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Public
  Financial Management Act 2013 (No. 9 of 2013) Part 9 (ss.72-75,
  procurement -- no specific dollar threshold could be modelled since
  the delegated Regulations were not found/fetched this session);
  Company Haus (Registrar of Companies, Ministry of Commerce,
  Industries, Labour and Immigration/MCILI) under the Companies Act
  2009 (No. 1 of 2009) and the Business Names Act 2014 (No. 13 of
  2014); the Registrar of Foreign Investment (InvestSolomons/Foreign
  Investment Division) under the Foreign Investment Act 2005 (No. 7 of
  2005); and the Inland Revenue Division (IRD) TIN regime under the
  Income Tax Act (Cap 123). `governor.cljc`'s flagship check
  independently recomputes whether a filing's own declared investment
  activity is actually a member of the SET of activities specified on
  its own Foreign Investment Act 2005 s.21(2)(b) Certificate of
  Registration -- a PER-ENTITY DYNAMIC-SCOPE-LIST CONTAINMENT check,
  genuinely different from every sibling's flat-value-threshold /
  cross-record-aggregation / fixed-list-membership / ownership-
  percentage / tier-classification shapes surveyed this session (grep
  across ~179 sibling `governor.cljc` files, see `marketentry.facts`
  docstring). See the namespace docstrings for the full research trail
  and honestly-narrowed scope, including facts this iteration could NOT
  verify (e.g. a specific procurement-value dollar threshold, or a
  local-representative/agent provision).
- `src/statute/facts.cljk` -- general-law catalog: the Companies Act
  2009 (company law) and the Labour Act (Chapter 73, 1996 Edition,
  labour law).

Every citation is curl/WebFetch-verified against an official source
(`ird.gov.sb`, `commerce.gov.sb`, `solomonbusinessregistry.gov.sb`,
`investsolomons.gov.sb`); live `paclii.org` returned a Cloudflare "Just
a moment..." bot-detection challenge every time this iteration fetched
it directly this session (NOT bypassed, per hard rule) -- every PacLII
citation instead comes from a `web.archive.org` snapshot of the exact
same official PacLII page, fetched and read directly this session. No
live Ministry of Finance and Treasury (MOFT) site could be reached this
session (`moft.gov.sb`/`finance.gov.sb`/`treasury.gov.sb` failed DNS
resolution, `mof.gov.sb` timed out) -- see `marketentry.facts`'s
docstring for exactly which facts are live-verified vs.
archived-snapshot-verified vs. an honestly-flagged gap.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Solomon Islands:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one. Solomon
Islands is thinly documented on English Wikipedia; this catalog carries 6
verified entries rather than pad with unverifiable claims (see the
`culture.facts` ns docstring and the commit history for dropped
candidates).
