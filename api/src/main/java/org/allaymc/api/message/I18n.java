package org.allaymc.api.message;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;

/**
 * {@code I18n} translates namespaced message keys and prepares vanilla keys for client-side
 * translation. Default overloads use the configured language and no formatting arguments unless
 * supplied explicitly; implementations provide key discovery and server-side lookup.
 *
 * <p>
 * {@code toClientFriendlyStyle0} distinguishes the vanilla namespace from server-owned namespaces.
 * Vanilla keys lose only their namespace and keep any percent starter and surrounding text so the
 * client can recognize them. Other namespaces use server translation. This conversion assumes key
 * discovery supplies a valid colon index; absent or malformed namespaced keys can fail during
 * substring extraction rather than returning unchanged text.
 *
 * @author daoge_cmd
 */
public interface I18n {

    /**
     * {@code FALLBACK_LANG} selects the English catalog when a requested-language lookup has no
     * translation for the discovered key.
     */
    LangCode FALLBACK_LANG = LangCode.en_US;
    /**
     * {@code I18N} holds the translation implementation exposed by {@code get}; initialization is
     * supplied through the API instance holder.
     */
    AllayAPI.APIInstanceHolder<I18n> I18N = AllayAPI.APIInstanceHolder.create();
    /**
     * {@code VANILLA_LANG_NAMESPACE} identifies keys left for client translation after their
     * namespace is removed from the outgoing message.
     */
    String VANILLA_LANG_NAMESPACE = Identifier.DEFAULT_NAMESPACE;

    /**
     * Returns the translation implementation currently installed in the API holder.
     */
    static I18n get() {
        return I18N.get();
    }

    /**
     * Returns whether the character is allowed inside a translation key. The accepted set contains
     * ASCII letters and digits, underscores, hyphens, periods, and colons; percent is a separate
     * key starter.
     */
    static boolean isValidKeyCharacter(char character) {
        return character == '_' || character == '-' ||
               character >= 'a' && character <= 'z' ||
               character >= 'A' && character <= 'Z' ||
               character >= '0' && character <= '9' ||
               character == '.' || character == ':';
    }

    /**
     * Translates the discovered message key using the configured default language and no formatting
     * arguments. Missing translations leave the source text unchanged; only the discovered key span
     * is replaced.
     */
    default String tr(@MayContainTrKey String tr) {
        return tr(getDefaultLangCode(), tr);
    }

    /**
     * Translates the discovered message key using the configured default language and the supplied
     * formatting arguments. Missing translations leave the source text unchanged; only the
     * discovered key span is replaced.
     */
    default String tr(@MayContainTrKey String tr, Object... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    /**
     * Translates the discovered message key using the requested language and no formatting
     * arguments. Missing translations leave the source text unchanged; only the discovered key span
     * is replaced.
     */
    default String tr(LangCode langCode, @MayContainTrKey String tr) {
        return tr(langCode, tr, new Object[0]);
    }

    /**
     * Translates the discovered message key using the requested language and the supplied
     * formatting arguments. Missing translations leave the source text unchanged; only the
     * discovered key span is replaced.
     */
    String tr(LangCode langCode, @MayContainTrKey String tr, Object... args);

    /**
     * Finds the candidate key span beginning after the first percent starter, or at the start of
     * the text when no starter exists. The result can lack a colon; malformed multiple-colon
     * candidates use {@code KeyInfo.EMPTY}.
     */
    KeyInfo findI18nKey(@MayContainTrKey String str);

    /**
     * Returns text prepared for client translation using the configured default language. Vanilla
     * namespaces are removed; other keys are translated on the server, and the input must contain a
     * discoverable namespaced key.
     */
    default String toClientFriendlyStyle(@MayContainTrKey String tr) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr);
    }

    /**
     * Returns text prepared for client translation using the configured default language. Vanilla
     * namespaces are removed; other keys are translated on the server, and the input must contain a
     * discoverable namespaced key.
     */
    default String toClientFriendlyStyle(@MayContainTrKey String tr, Object... args) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr, args);
    }

    /**
     * Returns text prepared for client translation using the requested language. Vanilla namespaces
     * are removed; other keys are translated on the server, and the input must contain a
     * discoverable namespaced key.
     */
    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr) {
        return toClientFriendlyStyle(langCode, tr, new Object[0]);
    }

    /**
     * Returns text prepared for client translation using the requested language. Vanilla namespaces
     * are removed; other keys are translated on the server, and the input must contain a
     * discoverable namespaced key.
     */
    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr, Object... args) {
        return toClientFriendlyStyle0(langCode, tr, args).left();
    }

    /**
     * Prepares a namespaced message for client or server translation and reports which path was
     * used. The input must contain a discovered key with a valid colon index.
     *
     * <p>
     * Vanilla keys retain their surrounding text and optional percent starter while losing the
     * namespace, allowing the client to interpret the same key location. The returned flag is
     * {@code true} for that path. Other namespaces delegate to server translation and return
     * {@code false}; absent or invalid key metadata can cause substring bounds failures.
     */
    default Pair<String, Boolean> toClientFriendlyStyle0(LangCode langCode, @MayContainTrKey String tr, Object... args) {
        var keyInfo = findI18nKey(tr);
        var namespace = tr.substring(keyInfo.hasStarter() ? keyInfo.startIndex() + 1 : keyInfo.startIndex(), keyInfo.colonIndex());
        if (VANILLA_LANG_NAMESPACE.equals(namespace)) {
            // Keep the percent starter and preceding text because they identify
            // the vanilla key at its original position for client-side translation.
            var prefix = keyInfo.hasStarter() ? tr.substring(0, keyInfo.startIndex() + 1) : "";
            return new Pair<>(prefix + tr.substring(keyInfo.colonIndex() + 1), true);
        }

        return new Pair<>(tr(langCode, tr, args), false);
    }

    /**
     * Returns the language used by translation overloads without an explicit language.
     */
    LangCode getDefaultLangCode();

    /**
     * Stores the language used by subsequent translation overloads without an explicit language.
     */
    void setDefaultLangCode(LangCode langCode);

    /**
     * {@code KeyInfo} describes a discovered candidate key and its character positions in the
     * source message.
     *
     * @param startIndex first key character position, including a percent starter when present
     * @param endIndex inclusive final key character position
     * @param colonIndex namespace separator position, or {@code -1} when absent
     * @param key discovered key text without the percent starter
     * @param hasStarter whether the span includes a percent starter
     */
    record KeyInfo(int startIndex, int endIndex, int colonIndex, String key, boolean hasStarter) {
        /**
         * {@code EMPTY} marks a candidate rejected for multiple colons, using negative indexes, a
         * {@code null} key, and no starter flag.
         */
        public static final KeyInfo EMPTY = new KeyInfo(-1, -1, -1, null, false);
    }
}
