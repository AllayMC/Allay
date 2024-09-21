package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Utils;

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
     * @return true if the character is a valid key character, otherwise false
     */
    static boolean isValidKeyCharacter(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.' || character == ':';
    }

    /**
     * Translate the text which may contain translation key.
     *
     * @param langCode the lang code used.
     * @param tr the text, which may contain translation key.
     * @param args the arguments used in the translation.
     * @return the translated text.
     */
    String tr(LangCode langCode, @MayContainTrKey String tr, String... args);

    /**
     * Translate the text which may contain translation key.
     *
     * @param langCode the lang code used.
     * @param tr the text, which may contain translation key.
     * @param args the arguments used in the translation.
     * @return the translated text.
     */
    default String tr(LangCode langCode, @MayContainTrKey String tr, Object... args) {
        return tr(langCode, tr, Utils.objectArrayToStringArray(args));
    }

    /**
     * Translate the text which may contain translation key, using the default lang code.
     *
     * @param tr the text, which may contain translation key.
     * @param args the arguments used in the translation.
     * @return the translated text.
     */
    default String tr(@MayContainTrKey String tr, String... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    /**
     * Translate the text which may contain translation key, using the default lang code.
     *
     * @param tr the text, which may contain translation key.
     * @param args the arguments used in the translation.
     * @return the translated text.
     */
    default String tr(@MayContainTrKey String tr, Object... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    /**
     * Translate the text which may contain translation key.
     *
     * @param langCode the lang code used.
     * @param tr the text, which may contain translation key.
     * @return the translated text.
     */
    String tr(LangCode langCode, @MayContainTrKey String tr);

    /**
     * Translate the text which may contain translation key, using the default lang code.
     *
     * @param tr the text, which may contain translation key.
     * @return the translated text.
     */
    default String tr(@MayContainTrKey String tr) {
        return tr(getDefaultLangCode(), tr);
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
     * Find the I18n key info in the string.
     *
     * @param str the string.
     * @return the I18n key info.
     */
    KeyInfo findI18nKey(@MayContainTrKey String str);

    default String toClientFriendlyStyle(@MayContainTrKey String tr) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr);
    }

    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr) {
        return toClientFriendlyStyle(langCode, tr, Utils.EMPTY_STRING_ARRAY);
    }

    default String toClientFriendlyStyle(@MayContainTrKey String tr, String... args) {
        return toClientFriendlyStyle(getDefaultLangCode(), tr, args);
    }

    default String toClientFriendlyStyle(LangCode langCode, @MayContainTrKey String tr, String... args) {
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
     * @param tr the translation key.
     * @param args the arguments.
     * @return the converted text and whether the translation key is vanilla.
     */
    default Pair<String, Boolean> toClientFriendlyStyle0(LangCode langCode, @MayContainTrKey String tr, String... args) {
        var keyInfo = findI18nKey(tr);
        var namespace = tr.substring(keyInfo.hasStarter ? keyInfo.startIndex + 1 : keyInfo.startIndex, keyInfo.colonIndex);
        if (VANILLA_LANG_NAMESPACE.equals(namespace)) {
            String left;
            if (keyInfo.hasStarter) {
                // Preserve '%'
                left = tr.substring(0, keyInfo.startIndex + 1);
            } else {
                left = "";
            }
            return Pair.of(left + tr.substring(keyInfo.colonIndex + 1), true);
        } else {
            return Pair.of(tr(langCode, tr, args), false);
        }

    }

    /**
     * KeyInfo stores the information of the translation key in a string.
     *
     * @param startIndex the start index of the translation key.
     * @param endIndex the end index of the translation key.
     * @param colonIndex the colon (":" character) index of the translation key.
     * @param key the translation key.
     * @param hasStarter whether the translation key has a starter character ("%" character).
     */
    record KeyInfo(int startIndex, int endIndex, int colonIndex, String key, boolean hasStarter) {
        public static final KeyInfo EMPTY = new KeyInfo(-1, -1, -1, null, false);
    }
}
