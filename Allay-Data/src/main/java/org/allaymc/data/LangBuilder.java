package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.i18n.LangCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class LangBuilder {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static final Map<LangCode, Map<String, String>> LANG_BUILT = new HashMap<>(LangCode.values().length);

    public static void main(String[] args) {
        for (var langCode : LangCode.values()) {
            LANG_BUILT.put(langCode, buildLang(langCode));
        }
        LANG_BUILT.forEach((langCode, lang) -> {
            var json = GSON.toJson(lang);
            var path = Path.of("Allay-Data/resources/lang/" + langCode.name() + ".json");
            if (!Files.exists(path.getParent())) {
                try {
                    Files.createDirectories(path.getParent());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Files.deleteIfExists(path);
                Files.createFile(path);
                Files.writeString(path, json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SneakyThrows
    public static Map<String, String> buildLang(LangCode langCode) {
        var lang = new TreeMap<String, String>();
        Files.readAllLines(Path.of("Allay-Data/resources/unpacked/lang_raw/vanilla/" + langCode.name() + ".lang")).forEach(line -> {
            if (!line.contains("=")) {
                return;
            }
            if (line.contains("#")) {
                line = fastTwoPartSplit(line, "#", "")[0];
            }
            var split = fastTwoPartSplit(line, "=", "");
            lang.put("minecraft:" + split[0], split[1].replaceAll("\t", ""));
        });
        var allayLangPath = Path.of("Allay-Data/resources/unpacked/lang_raw/allay/" + langCode.name() + ".json");
        if (Files.exists(allayLangPath)) {
            JsonParser.parseReader(Files.newBufferedReader(allayLangPath)).getAsJsonObject().entrySet().forEach(
                    entry -> {
                        var key = entry.getKey();
                        var value = entry.getValue().getAsString();
                        lang.put("allay:" + key, value);
                    }
            );
        }
        return lang;
    }
}
