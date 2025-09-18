package org.allaymc.server.message;

import org.allaymc.api.message.LangCode;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface I18nLoader {
    Map<String, String> getLangMap(LangCode langCode);
}
