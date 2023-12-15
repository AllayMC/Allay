package org.allaymc.server.i18n;

import com.google.gson.JsonParser;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.LangCode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class AllayI18nLoader implements I18nLoader {
    @Override
    public Map<String, String> getLangMap(LangCode langCode) {
        try (
                var input = new InputStreamReader(Objects.requireNonNull(AllayI18nLoader.class.getResourceAsStream("/lang/" + langCode.name() + ".json")));
        ) {
            var langMap = new HashMap<String, String>();
            var parser = JsonParser.parseReader(input);
            parser.getAsJsonObject().entrySet().forEach(entry -> {
                langMap.put(entry.getKey(), entry.getValue().getAsString());
            });
            return langMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
