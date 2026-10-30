package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.LangCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.allaymc.api.utils.AllayStringUtils.fastTwoPartSplit;

/**
 * {@code LangBuilder} builds the server's namespaced language resources from
 * vanilla text and optional Allay JSON translations. Each language is assembled
 * in a sorted map before any output files are written. Vanilla entries are
 * filtered by their original key prefixes, then receive a Minecraft namespace;
 * Allay entries receive their own namespace and are not subject to that filter.
 *
 * <p>
 * Text parsing keeps only content before the first comment marker, splits at
 * the first equals sign, and removes tabs from values. Vanilla keys already
 * containing a colon are discarded to avoid prefixing an incompatible key
 * shape. Output replacement is per language: a filesystem failure is logged for
 * that file and does not roll back resources already written.
 *
 * @author daoge_cmd
 */
@Slf4j
public class LangBuilder {

    /**
     * {@code GSON} serializes sorted translations with indentation while
     * preserving literal HTML characters in the generated language JSON.
     */
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * {@code LANG_BUILT} holds assembled maps between the read and write
     * phases. {@code main} replaces known languages but retains this publicly
     * mutable shared map.
     */
    public static final Map<LangCode, Map<String, String>> LANG_BUILT = new HashMap<>(LangCode.values().length);
    /**
     * {@code NOT_INCLUDES} lists literal vanilla-key prefixes excluded before
     * namespacing. Callers modifying this shared array change subsequent
     * {@code shouldInclude} results.
     */
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

    /**
     * Builds every supported language and replaces its generated JSON resource.
     * {@code args} is ignored, and paths are resolved from the repository root.
     * A build failure aborts before the output phase, although previously
     * assembled maps remain in the shared cache.
     *
     * <p>
     * Output failures are logged independently so other languages can still be
     * written. Existing files are deleted before recreation, without rollback;
     * a failed replacement can therefore remove the previous language resource.
     * Nonbreaking spaces are normalized in the serialized output only.
     */
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
                    // Skip only this language when its destination directory cannot
                    // be created; the remaining assembled translations can still be written.
                    log.error("Failed to create lang file directory for lang code {}", langCode.name(), e);
                    return;
                }
            }
            try {
                // Replace nonbreaking spaces only in serialized output; LANG_BUILT
                // retains the original characters for callers inspecting the assembled maps.
                json = json.replace(" ", " ");
                // Replacement removes the old file before writing the new contents;
                // a later failure cannot restore that language's previous resource automatically.
                Files.deleteIfExists(path);
                Files.createFile(path);
                Utils.writeFileWithCRLF(path, json);
            } catch (IOException e) {
                // Keep writing other languages after this replacement fails; the
                // logged path identifies which output may now be absent or incomplete.
                log.error("Failed to write lang file for lang code {}", langCode.name(), e);
            }
        });
    }

    /**
     * Assembles a new sorted translation map for one supported language.
     * Vanilla lines without an equals sign are ignored, comment suffixes are
     * removed, and accepted keys receive the Minecraft namespace. Repeated keys
     * replace earlier values after key stripping; values lose tab characters
     * but otherwise keep their text.
     *
     * <p>
     * Optional Allay JSON entries are added with the Allay namespace after the
     * vanilla pass. Missing optional files contribute nothing, while input or
     * parsing failures propagate instead of returning a partial map. The caller
     * owns the returned mutable map.
     */
    @SneakyThrows
    public static Map<String, String> buildLang(LangCode langCode) {
        var lang = new TreeMap<String, String>();
        Files.readAllLines(Path.of("data/resources/unpacked/lang_raw/vanilla/" + langCode.name() + ".lang")).forEach(line -> {
            if (!line.contains("=")) {
                return;
            }
            if (line.contains("#")) {
                // Treat the first hash as the start of a comment, including
                // inside a value; downstream translations therefore receive only its preceding text.
                line = fastTwoPartSplit(line, "#", "")[0];
            }
            var split = fastTwoPartSplit(line, "=", "");
            if (split[0].contains(":")) {
                // Embedded namespaces such as attribute.name.minecraft:attack_damage
                // cannot receive the same leading namespace as ordinary vanilla keys, so omit them.
                return;
            }
            if (shouldInclude(split[0])) {
                lang.put("minecraft:" + split[0].strip(), split[1].replaceAll("\t", ""));
            }
        });
        var allayLangPath = Path.of("data/resources/unpacked/lang_raw/allay/" + langCode.name() + ".json");
        // Allay translations are optional for each language; keep the assembled
        // vanilla entries available when there is no additional JSON source to merge.
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

    /**
     * Returns whether an original vanilla key avoids every excluded prefix.
     * Matching is literal, case-sensitive, and performed before whitespace
     * stripping or namespacing; the exclusion need not end at a key segment
     * boundary.
     */
    protected static boolean shouldInclude(String originKey) {
        for (var notInclude : NOT_INCLUDES) {
            if (originKey.startsWith(notInclude)) {
                return false;
            }
        }
        return true;
    }
}
