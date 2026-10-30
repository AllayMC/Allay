package org.allaymc.server.message;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.min;
import static org.allaymc.api.message.I18n.KeyInfo.EMPTY;
import static org.allaymc.api.message.I18n.isValidKeyCharacter;

/**
 * {@code AllayI18n} translates the first discovered message-key span through language catalogs and
 * applies string formatting arguments. A requested-language miss falls back to English; a missing
 * translation leaves the original message intact. Catalog loaders merge entries into the existing
 * maps rather than replacing them.
 *
 * <p>
 * Formatting consumes unnumbered percent markers first, then maps remaining arguments to numbered
 * markers beginning at one. Java-style numbered suffixes are normalized before that second pass,
 * preserving which arguments remain after unnumbered consumption. Numbered replacement searches by
 * textual prefix and replaces two characters, so multi-digit markers are not parsed as complete
 * indexes. Replacement text is searched again, and an argument containing its own numbered marker
 * can prevent termination. Callers coordinate shared catalog and default-language changes.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayI18n implements I18n {

    /**
     * Unnumbered string substitution marker.
     */
    // DISORDERED_PARAM_S and DISORDERED_PARAM_D consume arguments in textual
    // order before numbered substitutions use the arguments remaining after them.
    public static final String DISORDERED_PARAM_S = "%s";
    /**
     * Unnumbered numeric substitution marker.
     */
    public static final String DISORDERED_PARAM_D = "%d";

    /**
     * {@code langMap} retains merged catalogs by locale. Loader application updates existing maps
     * in place, and translation reads them without synchronization.
     */
    protected Map<LangCode, Map<String, String>> langMap = new EnumMap<>(LangCode.class);
    /**
     * Language used by default translation overloads.
     */
    @Getter
    @Setter
    protected LangCode defaultLangCode;

    /**
     * Creates a translator by merging the supplied loader's catalogs before storing the default
     * language. Per-language loader exceptions are logged and suppressed, so the created translator
     * can contain only partially loaded catalogs.
     */
    public AllayI18n(I18nLoader i18nLoader, LangCode defaultLangCode) {
        applyI18nLoader(i18nLoader);
        this.defaultLangCode = defaultLangCode;
    }

    /**
     * Translates the discovered key span and substitutes formatting arguments without changing
     * surrounding message text. A rejected key or missing translation returns the original input.
     *
     * <p>
     * Arguments are converted through their {@code toString} implementations, so {@code null}
     * argument elements fail. Unnumbered markers consume arguments before numbered replacement uses
     * the remainder. Only one discovered key is processed. Numbered replacement scans substituted
     * text repeatedly; callers avoid self-reproducing markers in argument values because the loop
     * has no cycle guard or iteration bound.
     */
    @Override
    public String tr(LangCode langCode, String tr, Object... args) {
        var keyInfo = findI18nKey(tr);
        if (keyInfo == EMPTY) {
            return tr;
        }

        var translation = getTranslation(langCode, keyInfo.key());
        // Neither requested nor fallback catalog supplied this key, so preserve
        // the complete source message instead of losing its unresolved translation text.
        if (translation == null) {
            return tr;
        }

        var strArgs = Utils.objectArrayToStringArray(args);

        var result = replaceUnorderedParams(translation, strArgs);
        translation = replaceOrderedParams(result.left(), result.right(), strArgs);

        return new StringBuilder(tr).replace(keyInfo.startIndex(), keyInfo.endIndex() + 1, translation).toString();
    }

    /**
     * Scans a candidate beginning after the first percent starter, or at index zero when no starter
     * exists. Allowed characters extend the candidate until the first delimiter.
     *
     * <p>
     * The returned indexes refer to the original message, with the optional percent included in the
     * replacement span but omitted from {@code key}. A second colon rejects the candidate with
     * {@code EMPTY}; an absent colon or empty key still yields ordinary metadata. Callers requiring
     * a namespaced key must account for those incomplete candidates.
     */
    @Override
    public KeyInfo findI18nKey(String str) {
        var startIndex = str.indexOf("%");
        var hasStarter = startIndex != -1;
        var index = startIndex + 1;
        var colonIndex = -1;
        var keyBuilder = new StringBuilder();
        for (/**/; index < str.length(); index++) {
            var c = str.charAt(index);
            if (c == ':') {
                if (colonIndex != -1) {
                    // Multiple namespace separators make the candidate ambiguous;
                    // reject the whole candidate instead of translating a partial key.
                    return EMPTY;
                }
                colonIndex = index;
            }

            if (isValidKeyCharacter(c)) {
                keyBuilder.append(c);
            } else {
                break;
            }
        }

        return new KeyInfo(hasStarter ? startIndex : 0, index - 1, colonIndex, keyBuilder.toString(), hasStarter);
    }

    /**
     * Merges each locale supplied by the loader into the existing catalog map. Existing entries not
     * overwritten by the loader remain available, and an exception for one locale is logged before
     * later locales are attempted.
     */
    public void applyI18nLoader(I18nLoader i18nLoader) {
        for (var langCode : LangCode.values()) {
            try {
                var map = langMap.computeIfAbsent(langCode, $ -> new Object2ObjectOpenHashMap<>());
                map.putAll(i18nLoader.getLangMap(langCode));
            } catch (Exception e) {
                // A missing or malformed catalog affects this locale only; retain
                // entries already merged and continue attempting the remaining locale catalogs.
                log.error("Error in applying i18n loader for lang code {}", langCode, e);
            }
        }
    }

    /**
     * Returns a requested-language translation, falling back to the English catalog when no value
     * exists. A miss in both catalogs returns {@code null}, allowing {@code tr} to retain the
     * unresolved source message.
     */
    private String getTranslation(LangCode langCode, String key) {
        var translation = langMap.getOrDefault(langCode, Map.of()).get(key);
        // Locale catalogs can omit individual keys; use the English entry for
        // that key without replacing the requested catalog for other lookups.
        if (translation == null) {
            translation = langMap.getOrDefault(FALLBACK_LANG, Map.of()).get(key);
        }
        return translation;
    }

    /**
     * Replaces unnumbered string and numeric markers from left to right until arguments are
     * exhausted. The returned count identifies how many arguments the later numbered pass must
     * skip.
     *
     * <p>
     * The method then rewrites supported numbered {@code $s} and {@code $d} suffixes into bare
     * numbered markers. This keeps the next phase independent of the original formatter suffix
     * while retaining its remaining-argument offset. Unfilled unnumbered markers remain in the
     * text, and inserted argument text can itself supply a marker consumed by a later argument.
     */
    private Pair<String, Integer> replaceUnorderedParams(String text, String[] args) {
        int argIndex = 0;
        int paramIndex;
        while ((paramIndex = findUnorderedParamIndex(text)) != -1 && argIndex < args.length) {
            text = new StringBuilder(text).replace(paramIndex, paramIndex + 2, args[argIndex++]).toString();
        }

        for (int order = 1; order <= args.length - argIndex + 1; order++) {
            // The numbered phase accepts bare markers, so normalize supported
            // Java-style suffixes after determining how many unnumbered arguments were consumed.
            text = text.replaceAll("%" + order + "\\$s", "%" + order);
            text = text.replaceAll("%" + order + "\\$d", "%" + order);
        }
        return new Pair<>(text, argIndex);
    }

    /**
     * Replaces numbered textual markers using arguments beginning at the supplied offset,
     * restarting numbering at one. Each argument replaces every matching occurrence before the next
     * argument is considered.
     *
     * <p>
     * The search matches a marker prefix and replacement removes two characters rather than parsing
     * an arbitrary-length index. Multi-digit indexes can therefore be partially replaced. Inserted
     * text is searched again for the same order; a self-reproducing argument can make the loop
     * nonterminating because no progress or cycle check limits the repeated substitution.
     */
    private String replaceOrderedParams(String text, int startArgIndex, String[] args) {
        int order = 1;
        for (int i = startArgIndex; i < args.length; i++, order++) {
            int paramIndex;
            while ((paramIndex = findOrderedParamIndex(text, order)) != -1) {
                text = new StringBuilder(text).replace(paramIndex, paramIndex + 2, args[i]).toString();
            }
        }
        return text;
    }

    /**
     * Returns the earliest unnumbered string or numeric marker position, or {@code -1} when neither
     * exists. The result orders consumption across both marker kinds rather than preferring one
     * argument format.
     */
    protected int findUnorderedParamIndex(String str) {
        var indexS = str.indexOf(DISORDERED_PARAM_S);
        var indexD = str.indexOf(DISORDERED_PARAM_D);
        if (indexS == -1) {
            return indexD;
        } else if (indexD == -1) {
            return indexS;
        } else {
            return min(indexS, indexD);
        }
    }

    /**
     * Returns the first textual position of the supplied percent-number prefix, or {@code -1} when
     * absent. The search does not check marker boundaries, so order one also matches the beginning
     * of order ten.
     */
    protected int findOrderedParamIndex(String str, int order) {
        return str.indexOf("%" + order);
    }
}
