package org.allaymc.api.message;

import lombok.AllArgsConstructor;

/**
 * {@code LangCode} identifies supported language catalogs using client locale names and
 * human-readable display labels. {@code byName} matches enum identifiers with optional case
 * folding, while {@code toString} returns the display label rather than the identifier used for
 * catalog lookup.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public enum LangCode {
    /**
     * US English locale.
     */
    en_US("English (United States)"),
    /**
     * UK English locale.
     */
    en_GB("English (United Kingdom)"),
    /**
     * German Germany locale.
     */
    de_DE("Deutsch (Deutschland)"),
    /**
     * Spanish Spain locale.
     */
    es_ES("Español (España)"),
    /**
     * Spanish Mexico locale.
     */
    es_MX("Español (México)"),
    /**
     * French France locale.
     */
    fr_FR("Français (France)"),
    /**
     * French Canada locale.
     */
    fr_CA("Français (Canada)"),
    /**
     * Italian Italy locale.
     */
    it_IT("Italiano (Italia)"),
    /**
     * Japanese Japan locale.
     */
    ja_JP("日本語 (日本)"),
    /**
     * Korean Korea locale.
     */
    ko_KR("한국어 (대한민국)"),
    /**
     * Portuguese Brazil locale.
     */
    pt_BR("Português (Brasil)"),
    /**
     * Portuguese Portugal locale.
     */
    pt_PT("Português (Portugal)"),
    /**
     * Russian Russia locale.
     */
    ru_RU("Русский (Россия)"),
    /**
     * Simplified Chinese locale.
     */
    zh_CN("中文(简体)"),
    /**
     * Traditional Chinese locale.
     */
    zh_TW("中文(繁體)"),
    /**
     * Dutch Netherlands locale.
     */
    nl_NL("Nederlands (Nederland)"),
    /**
     * Bulgarian Bulgaria locale.
     */
    bg_BG("Български (България)"),
    /**
     * Czech Czechia locale.
     */
    cs_CZ("Čeština (Česko)"),
    /**
     * Danish Denmark locale.
     */
    da_DK("Dansk (Danmark)"),
    /**
     * Greek Greece locale.
     */
    el_GR("Ελληνικά (Ελλάδα)"),
    /**
     * Finnish Finland locale.
     */
    fi_FI("Suomi (Suomi)"),
    /**
     * Hungarian Hungary locale.
     */
    hu_HU("Magyar (Magyarország)"),
    /**
     * Indonesian Indonesia locale.
     */
    id_ID("Indonesia (Indonesia)"),
    /**
     * Norwegian Bokmal locale.
     */
    nb_NO("Norsk bokmål (Norge)"),
    /**
     * Polish Poland locale.
     */
    pl_PL("Polski (Polska)"),
    /**
     * Slovak Slovakia locale.
     */
    sk_SK("Slovenčina (Slovensko)"),
    /**
     * Swedish Sweden locale.
     */
    sv_SE("Svenska (Sverige)"),
    /**
     * Turkish Turkey locale.
     */
    tr_TR("Türkçe (Türkiye)"),
    /**
     * Ukrainian Ukraine locale.
     */
    uk_UA("Українська (Україна)");

    /**
     * {@code VALUES} caches the supported locale constants for name lookup, avoiding a fresh
     * enum-values array for every search.
     */
    private static final LangCode[] VALUES = values();

    /**
     * Human-readable language display label.
     */
    private final String string;

    /**
     * Finds a locale by its enum identifier using case-insensitive matching. Unknown names,
     * including {@code null}, return {@code null}; display labels returned by {@code toString}
     * are not lookup keys.
     */
    public static LangCode byName(String name) {
        return byName(name, true);
    }

    /**
     * Finds a locale by its enum identifier with optional case-insensitive matching. Exact matches
     * are always accepted, while unknown names and {@code null} return {@code null} without
     * selecting a fallback locale.
     */
    public static LangCode byName(String name, boolean ignoreCase) {
        for (LangCode langCode : VALUES) {
            if (langCode.name().equals(name)) {
                return langCode;
            }
            if (ignoreCase && langCode.name().equalsIgnoreCase(name)) {
                return langCode;
            }
        }
        return null;
    }

    /**
     * Returns this locale's human-readable display label rather than its enum identifier.
     */
    @Override
    public String toString() {
        return this.string;
    }
}
