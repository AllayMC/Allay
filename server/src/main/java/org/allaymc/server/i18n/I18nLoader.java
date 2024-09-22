package org.allaymc.server.i18n;

import org.allaymc.api.i18n.LangCode;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface I18nLoader {
    Map<String, String> getLangMap(LangCode langCode);
}
