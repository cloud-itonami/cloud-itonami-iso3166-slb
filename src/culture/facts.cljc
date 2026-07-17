(ns culture.facts
  "Country-level regional-culture catalog for Solomon Islands (SLB) --
  national dishes, protected products, beverages, crafts, festivals and
  heritage sites, per ADR-2607171400 addendum 2 (cloud-itonami-
  municipality-culture-catalog Wave 1, in com-junkawasaki/root). Sibling
  namespace to `marketentry.facts` / `statute.facts` (ADR-2607141700);
  city-level counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Solomon Islands is thinly documented on English Wikipedia: several
  candidate dishes (e.g. a Solomon-specific raw-fish salad) could not be
  verified as country-specific and were dropped rather than padded (see
  README / commit message for the drop list). This catalog stops at 6
  entries -- below the usual 6-10 target's 3-dish minimum -- rather than
  fabricate additional coverage.

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"SLB"
   [{:culture/id "slb.dish.poi"
     :culture/name "Poi"
     :culture/country "SLB"
     :culture/kind :dish
     :culture/summary "Fermented taro-root paste, described as served during any Solomon Islands celebration, and preparable with chicken or fish or as a porridge."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Solomon_Islands"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "slb.dish.ulu"
     :culture/name "Ulu (breadfruit)"
     :culture/country "SLB"
     :culture/kind :dish
     :culture/summary "Breadfruit, locally called ulu, described as servable with any dish and brought to the islands by Lapita seafarers."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Solomon_Islands"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "slb.beverage.kava"
     :culture/name "Kava"
     :culture/country "SLB"
     :culture/kind :beverage
     :culture/summary "Identified as the national drink of the Solomon Islands."
     :culture/url "https://en.wikipedia.org/wiki/Cuisine_of_Solomon_Islands"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "slb.product.ngali-nut"
     :culture/name "Ngali nut"
     :culture/country "SLB"
     :culture/kind :product
     :culture/summary "Canarium indicum, known locally as ngali nut; important in traditional Solomon Islands society where ownership of the trees is a measure of wealth, with commercial processing and marketing having started there in 1989."
     :culture/url "https://en.wikipedia.org/wiki/Canarium_indicum"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "slb.craft.shell-money"
     :culture/name "Shell money"
     :culture/country "SLB"
     :culture/kind :craft
     :culture/summary "Traditional currency of small shells laboriously ground by hand; as late as 1882 local Solomon Islands trade was carried on by this coinage, and cowry-shell currency worked into decorated strips remains in use to some extent today."
     :culture/url "https://en.wikipedia.org/wiki/Shell_money"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "slb.heritage.east-rennell"
     :culture/name "East Rennell"
     :culture/country "SLB"
     :culture/kind :heritage
     :culture/summary "Southern portion of Rennell Island -- the largest raised coral atoll in the world -- inscribed as a UNESCO World Heritage Site in 1998; added to the List of World Heritage in Danger in 2013 due to the threat of logging."
     :culture/url "https://en.wikipedia.org/wiki/East_Rennell"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-slb culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "SLB"))
                 " SLB entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
