package org.allaymc.server.message;

import com.google.gson.reflect.TypeToken;
import org.allaymc.api.message.LangCode;
import org.allaymc.server.utils.JSONUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
public class AllayI18nLoader implements I18nLoader {
    @Override
    public Map<String, String> getLangMap(LangCode langCode) {
        try (var input = Objects.requireNonNull(AllayI18nLoader.class.getResourceAsStream("/lang/" + langCode.name() + ".json"))) {
            TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {
            };
            var bytes = input.readAllBytes();
            return JSONUtils.fromLenient(new String(bytes, StandardCharsets.UTF_8), typeToken);
        } catch (IOException e) {
            throw new I18nException(e);
        }
    }
}
