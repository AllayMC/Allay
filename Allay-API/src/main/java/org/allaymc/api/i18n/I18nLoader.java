package org.allaymc.api.i18n;

import java.util.Map;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public interface I18nLoader {
    Map<String, String> getLangMap(LangCode langCode);
}
