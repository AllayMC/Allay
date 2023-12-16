package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.ApiInstanceHolder;

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

    String tr(String tr, String... args);

    default String tr(String tr, Object... args) {
        String[] argsStr = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            argsStr[i] = args[i].toString();
        }
        return tr(tr, argsStr);
    }

    String tr(String tr);

    void setLangCode(LangCode langCode);

    LangCode getLangCode();

    Pair<String, Integer> findI18nKey(String str);
}
