package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.LangCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * @author daoge_cmd
 */
@Slf4j
public class LangBuilder {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static final Map<LangCode, Map<String, String>> LANG_BUILT = new HashMap<>(LangCode.values().length);
    public static final String[] NOT_INCLUDES = {
            "3d_export", "accessibility", "accounts", "addExternalServerScreen", "addServer",
            "apple", "authentication", "chooseRealmScreen", "clientscript", "codeScreen",
            "controller", "course", "createWorld", "credits", "crossPlatformToggle", "csbCreateScreen",
            "dayOneExperience", "demo", "dynamicPackage", "edu", "eula", "exports", "feed",
            "furnaceScreen", "gameArgument", "gameTip", "gamepad_disconnect", "gathering",
            "generator", "globalPauseScreen", "gui", "hostOption", "howtoplay", "hudScreen",
            "joincode", "lanServer", "lateJoinScreen", "lategameTips", "level", "livingroom",
            "localWorld", "manifestvalidation", "mcoServer", "meal", "menu", "merchant",
            "midgameTips", "network", "npcscreen", "offer", "options", "packdiscoveryerror",
            "packupgradewarning", "permissions", "playfab", "playscreen", "profileScreen",
            "progressScreen", "ratingPopUp", "raytracing", "realms", "recipeBook", "recipeToast",
            "resourcePack", "script", "seargeSays", "selectServer", "selectTemplate",
            "selectWorld", "sidebar", "skin", "soundCategory", "splitscreen", "start", "stat",
            "store", "storageManager", "storageSpaceWarningScreen", "storage_migration",
            "stream", "structure_block", "sunsetting", "survey", "thirdPartyWorld", "tips", "title",
            "trial", "tutorial", "typeface", "uiPackError", "updateScreen", "usermanagement", "userData",
            "utility", "verification", "worldConversionComplete", "worldConversionErrorPrompt",
            "worldError", "worldTemplate", "world_recovery", "xbl", "xbox"
    };

    public static void main(String[] args) {
        for (var langCode : LangCode.values()) {
            LANG_BUILT.put(langCode, buildLang(langCode));
        }
        LANG_BUILT.forEach((langCode, lang) -> {
            var json = GSON.toJson(lang);
            var path = Path.of("data/resources/lang/" + langCode.name() + ".json");
            if (!Files.exists(path.getParent())) {
                try {
                    Files.createDirectories(path.getParent());
                } catch (IOException e) {
                    log.error("Failed to create lang file directory for lang code {}", langCode.name(), e);
                    return;
                }
            }
            try {
                json = json.replace(" ", " ");
                Files.deleteIfExists(path);
                Files.createFile(path);
                Utils.writeFileWithCRLF(path, json);
            } catch (IOException e) {
                log.error("Failed to write lang file for lang code {}", langCode.name(), e);
            }
        });
    }

    @SneakyThrows
    public static Map<String, String> buildLang(LangCode langCode) {
        var lang = new TreeMap<String, String>();
        Files.readAllLines(Path.of("data/resources/unpacked/lang_raw/vanilla/" + langCode.name() + ".lang")).forEach(line -> {
            if (!line.contains("=")) {
                return;
            }
            if (line.contains("#")) {
                line = fastTwoPartSplit(line, "#", "")[0];
            }
            var split = fastTwoPartSplit(line, "=", "");
            if (split[0].contains(":")) {
                // 目前有几个形如“attribute.name.minecraft:attack_damage”, 不符合命名空间标识符格式
                // 懒得搞了，直接删除
                return;
            }
            if (shouldInclude(split[0])) {
                lang.put("minecraft:" + split[0], split[1].replaceAll("\t", ""));
            }
        });
        var allayLangPath = Path.of("data/resources/unpacked/lang_raw/allay/" + langCode.name() + ".json");
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

    protected static boolean shouldInclude(String originKey) {
        for (var notInclude : NOT_INCLUDES) {
            if (originKey.startsWith(notInclude)) {
                return false;
            }
        }
        return true;
    }
}
