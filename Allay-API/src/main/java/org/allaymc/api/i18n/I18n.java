package org.allaymc.api.i18n;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Utils;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface I18n {

    LangCode FALLBACK_LANG = LangCode.en_US;

    ApiInstanceHolder<I18n> I18N = ApiInstanceHolder.of();

    static I18n get() {
        return I18N.get();
    }

    String VANILLA_LANG_NAMESPACE = "minecraft";

    String tr(LangCode langCode, String tr, String... args);

    default String tr(LangCode langCode, String tr, Object... args) {
        return tr(langCode, tr, Utils.objectArrayToStringArray(args));
    }

    default String tr(String tr, String... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    default String tr(String tr, Object... args) {
        return tr(getDefaultLangCode(), tr, args);
    }

    String tr(LangCode langCode, String tr);

    default String tr(String tr) {
        return tr(getDefaultLangCode(), tr);
    }

    void setDefaultLangCode(LangCode langCode);

    LangCode getDefaultLangCode();

    record KeyInfo(int startIndex, int endIndex, int colonIndex, String key, boolean hasStarter) {
        public static final KeyInfo EMPTY = new KeyInfo(-1, -1, -1, null, false);
    }

    KeyInfo findI18nKey(String str);

    default String toClientStyle(String tr) {
        return toClientStyle(tr, Utils.EMPTY_STRING_ARRAY);
    }

    default String toClientStyle(String tr, String... args) {
        var keyInfo = findI18nKey(tr);
        var namespace = tr.substring(keyInfo.hasStarter ? keyInfo.startIndex + 1 : keyInfo.startIndex, keyInfo.colonIndex);
        if (VANILLA_LANG_NAMESPACE.equals(namespace)) {
            String left;
            if (keyInfo.hasStarter) {
                // 保留 '%'
                left = tr.substring(0, keyInfo.startIndex + 1);
            } else {
                left = "";
            }
            return left + tr.substring(keyInfo.colonIndex + 1);
        } else {
            return tr(tr, args);
        }
    }

    static boolean isValidKeyCharacter(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character >= '0' && character <= '9' || character == '.' || character == ':';
    }
}
