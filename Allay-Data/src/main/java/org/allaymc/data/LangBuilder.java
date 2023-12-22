package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.i18n.LangCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * Allay Project 2023/12/15
 *
 * @author daoge_cmd
 */
public class LangBuilder {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static final Map<LangCode, Map<String, String>> LANG_BUILT = new HashMap<>(LangCode.values().length);
    static final List<String> NOT_INCLUDES;

    static {
        NOT_INCLUDES = new ArrayList<>();
        NOT_INCLUDES.add("accessibility");
        NOT_INCLUDES.add("credits");
        NOT_INCLUDES.add("chooseRealmScreen");
        NOT_INCLUDES.add("eula");
        NOT_INCLUDES.add("accounts");
        NOT_INCLUDES.add("apple");
        NOT_INCLUDES.add("dynamicPackage");
        NOT_INCLUDES.add("gameTest");
        NOT_INCLUDES.add("clientscript");
        NOT_INCLUDES.add("script");
        NOT_INCLUDES.add("tips");
        NOT_INCLUDES.add("codeScreen");
        NOT_INCLUDES.add("course");
        NOT_INCLUDES.add("controller");
        NOT_INCLUDES.add("createWorld");
        NOT_INCLUDES.add("crossPlatformToggle");
        NOT_INCLUDES.add("realms");
        NOT_INCLUDES.add("csbCreateScreen");
        NOT_INCLUDES.add("raytracing");
        NOT_INCLUDES.add("network");
        NOT_INCLUDES.add("localWorld");
        NOT_INCLUDES.add("thirdPartyWorld");
        NOT_INCLUDES.add("xbl");
        NOT_INCLUDES.add("playfab");
        NOT_INCLUDES.add("store");
        NOT_INCLUDES.add("dayOneExperience");
        NOT_INCLUDES.add("demo");
        NOT_INCLUDES.add("csbCreateScreen");
        NOT_INCLUDES.add("edu");
        NOT_INCLUDES.add("addExternalServerScreen");
        NOT_INCLUDES.add("exports");
        NOT_INCLUDES.add("feed");
        NOT_INCLUDES.add("exports");
        NOT_INCLUDES.add("codeScreen");
        NOT_INCLUDES.add("xbox");
        NOT_INCLUDES.add("uiPackError");
        NOT_INCLUDES.add("typeface");
        NOT_INCLUDES.add("trial");
        NOT_INCLUDES.add("options");
        NOT_INCLUDES.add("midgameTips");
        NOT_INCLUDES.add("menu");
        NOT_INCLUDES.add("npcscreen");
        NOT_INCLUDES.add("howtoplay");
        NOT_INCLUDES.add("gui");
        NOT_INCLUDES.add("gathering");
        NOT_INCLUDES.add("gameTip");
        NOT_INCLUDES.add("hudScreen");
        NOT_INCLUDES.add("hostOption");
        NOT_INCLUDES.add("generator");
        NOT_INCLUDES.add("globalPauseScreen");
        NOT_INCLUDES.add("gamepad_disconnect");
        NOT_INCLUDES.add("gameArgument");
        NOT_INCLUDES.add("furnaceScreen");
        NOT_INCLUDES.add("profileScreen");
        NOT_INCLUDES.add("progressScreen");
        NOT_INCLUDES.add("recipeToast");
        NOT_INCLUDES.add("recipeBook");
        NOT_INCLUDES.add("ratingPopUp");
        NOT_INCLUDES.add("resourcePack");
        NOT_INCLUDES.add("seargeSays");
        NOT_INCLUDES.add("selectTemplate");
        NOT_INCLUDES.add("selectServer");
        NOT_INCLUDES.add("selectWorld");
        NOT_INCLUDES.add("sidebar");
        NOT_INCLUDES.add("skin");
        NOT_INCLUDES.add("soundCategory");
        NOT_INCLUDES.add("splitscreen");
        NOT_INCLUDES.add("start");
        NOT_INCLUDES.add("stat");
        NOT_INCLUDES.add("storageManager");
        NOT_INCLUDES.add("storageSpaceWarningScreen");
        NOT_INCLUDES.add("storage_migration");
        NOT_INCLUDES.add("stream");
        NOT_INCLUDES.add("structure_block");
        NOT_INCLUDES.add("survey");
        NOT_INCLUDES.add("sunsetting");
        NOT_INCLUDES.add("worldTemplate");
        NOT_INCLUDES.add("world_recovery");
        NOT_INCLUDES.add("tutorial");
        NOT_INCLUDES.add("updateScreen");
        NOT_INCLUDES.add("usermanagement");
        NOT_INCLUDES.add("utility");
        NOT_INCLUDES.add("verification");
        NOT_INCLUDES.add("worldConversionComplete");
        NOT_INCLUDES.add("worldError");
        NOT_INCLUDES.add("3d_export");
        NOT_INCLUDES.add("playscreen");
        NOT_INCLUDES.add("permissions");
        NOT_INCLUDES.add("packupgradewarning");
        NOT_INCLUDES.add("packdiscoveryerror");
        NOT_INCLUDES.add("offer");
        NOT_INCLUDES.add("mcoServer");
        NOT_INCLUDES.add("merchant");
        NOT_INCLUDES.add("livingroom");
        NOT_INCLUDES.add("manifestvalidation");
        NOT_INCLUDES.add("level");
        NOT_INCLUDES.add("lategameTips");
        NOT_INCLUDES.add("lanServer");
        NOT_INCLUDES.add("lateJoinScreen");
        NOT_INCLUDES.add("joincode");
        NOT_INCLUDES.add("worldConversionErrorPrompt");
        NOT_INCLUDES.add("userData");
        NOT_INCLUDES.add("title");
        NOT_INCLUDES.add("authentication");
        NOT_INCLUDES.add("addServer");
    }

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
            if (split[0].contains(":")){
                // 目前有几个形如“attribute.name.minecraft:attack_damage”, 不符合命名空间标识符格式
                // 懒得搞了，直接删除
                return;
            }
            if (shouldInclude(split[0])) {
                lang.put("minecraft:" + split[0], split[1].replaceAll("\t", ""));
            }
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

    protected static boolean shouldInclude(String originKey) {
        for (var notInclude : NOT_INCLUDES) {
            if (originKey.startsWith(notInclude)) {
                return false;
            }
        }
        return true;
    }
}
