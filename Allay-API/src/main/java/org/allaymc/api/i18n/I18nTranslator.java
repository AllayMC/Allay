package org.allaymc.api.i18n;

import it.unimi.dsi.fastutil.Pair;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface I18nTranslator {

    String VANILLA_LANG_NAMESPACE = "minecraft";

    String tr(String tr, String... args);

    String tr(String tr);

    void setLangCode(LangCode langCode);

    Pair<String, Integer> findI18nKey(String str);
}
