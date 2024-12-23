package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.ApiInstanceHolder;

/**
 * I18n is used to translate the text which contains translation key in the game.
 *
 * @author daoge_cmd
 */
public interface I18n {

    /**
     * The fallback lang code.
     * <p>
     * Fallback lang code is used when the translation key is not found in the specified lang code.
     */
    LangCode FALLBACK_LANG = LangCode.en_US;

    ApiInstanceHolder<I18n> I18N = ApiInstanceHolder.create();
    String VANILLA_LANG_NAMESPACE = "minecraft";

    /**
     * Get the I18n instance.
     *
     * @return the I18n instance
     */
    static I18n get() {
        return I18N.get();
    }

    /**
     * Check if the character is a valid key character.
     *
     * @param character the character
     *
     * @return true if the character is a valid key character, otherwise false
     */
    static boolean isValidKeyCharacter(char character) {
        return character == '_' || character == '-' ||
               character >= 'a' && character <= 'z' ||
               character >= 'A' && character <= 'Z' ||
               character >= '0' && character <= '9' ||
               character == '.' || character == ':';
    }

    /**
     * @see #tr(LangCode, String, Object...)
     */
    default String tr(@MayContainTrKey String tr) {
        return tr(getDefaultLangCode(), tr);
    }

    /**
     * @see #tr(LangCode, String, Object...)
     */
    default String tr(@MayContainTrKey String tr, Object... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    /**
     * @see #tr(LangCode, String, Object...)
     */
    default String tr(LangCode langCode, @MayContainTrKey String tr) {
        return tr(langCode, tr, new Object[0]);
    }

    /**
     * Translate the text which may contain translation key.
     *
     * @param langCode the lang code used.
     * @param tr       the text, which may contain translation key.
     * @param args     the arguments used in the translation.
     *
     * @return the translated text.
     */
    String tr(LangCode langCode, @MayContainTrKey String tr, Object... args);

    /**
     * Find the I18n key info in the string.
     *
     * @param str the string.
     *
     * @return the I18n key info.
     */
    KeyInfo findI18nKey(@MayContainTrKey String str);

    /**
     * @see #toClientFriendlyStyle0(LangCode, String, Object...)
     */
    default String toClientFriendlyStyle(@MayContainTrKey String tr) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr);
    }

    /**
     * @see #toClientFriendlyStyle0(LangCode, String, Object...)
     */
    default String toClientFriendlyStyle(@MayContainTrKey String tr, Object... args) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr, args);
    }

    /**
     * @see #toClientFriendlyStyle0(LangCode, String, Object...)
     */
    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr) {
        return toClientFriendlyStyle(langCode, tr, new Object[0]);
    }

    /**
     * @see #toClientFriendlyStyle0(LangCode, String, Object...)
     */
    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr, Object... args) {
        return toClientFriendlyStyle0(langCode, tr, args).first();
    }

    /**
     * Convert the translation key to client friendly style.
     * <p>
     * Translation key in allay is in the format of 'namespace:path'. Namespace "minecraft"
     * will be used for vanilla translation key, and "allay" will be used for allay translation key.
     * <p>
     * However, the translation key in vanilla (client) does not have namespace, so if we want to translate the
     * text client-side, we need to remove the namespace.
     * <p>
     * This method will find and remove the namespace in the translation key, itself does not translate the text.
     *
     * @param langCode the lang code.
     * @param tr       the translation key.
     * @param args     the arguments.
     *
     * @return the converted text and whether the translation key is vanilla.
     */
    default Pair<String, Boolean> toClientFriendlyStyle0(LangCode langCode, @MayContainTrKey String tr, Object... args) {
        var keyInfo = findI18nKey(tr);
        var namespace = tr.substring(keyInfo.hasStarter() ? keyInfo.startIndex() + 1 : keyInfo.startIndex(), keyInfo.colonIndex());
        if (VANILLA_LANG_NAMESPACE.equals(namespace)) {
            // Preserve '%'
            var prefix = keyInfo.hasStarter() ? tr.substring(0, keyInfo.startIndex() + 1) : "";
            return Pair.of(prefix + tr.substring(keyInfo.colonIndex() + 1), true);
        }

        return Pair.of(tr(langCode, tr, args), false);
    }

    /**
     * Get the default lang code.
     *
     * @return the default lang code.
     */
    LangCode getDefaultLangCode();

    /**
     * Set the default lang code.
     *
     * @param langCode the lang code.
     */
    void setDefaultLangCode(LangCode langCode);

    /**
     * KeyInfo stores the information of the translation key in a string.
     *
     * @param startIndex the start index of the translation key.
     * @param endIndex   the end index of the translation key.
     * @param colonIndex the colon (":" character) index of the translation key.
     * @param key        the translation key.
     * @param hasStarter whether the translation key has a starter character ("%" character).
     */
    record KeyInfo(int startIndex, int endIndex, int colonIndex, String key, boolean hasStarter) {
        public static final KeyInfo EMPTY = new KeyInfo(-1, -1, -1, null, false);
    }
}
