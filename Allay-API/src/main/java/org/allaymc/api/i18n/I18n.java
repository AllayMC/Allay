package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.utils.Utils;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface I18n {

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

    Pair<String, Integer> findI18nKey(String str);
}
