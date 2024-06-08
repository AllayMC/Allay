package org.allaymc.server.i18n;

import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.allaymc.api.i18n.I18nLoader;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.utils.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        try (var input = Objects.requireNonNull(AllayI18nLoader.class.getResourceAsStream("/lang/" + langCode.name() + ".json"))) {
            TypeToken<HashMap<String, String>> typeToken = new TypeToken<>() {};
            byte[] bytes = input.readAllBytes();
            return JSONUtils.fromLenient(new String(bytes, StandardCharsets.UTF_8),typeToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
