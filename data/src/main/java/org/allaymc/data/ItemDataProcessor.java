package org.allaymc.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code ItemDataProcessor} converts unpacked item metadata into the runtime
 * item resource. Every existing translation key receives the Minecraft
 * namespace prefix, while other members and items without translation keys
 * remain present. Paths are relative to the repository root, and the processed
 * output replaces the previous resource file.
 *
 * @author daoge_cmd
 */
public class ItemDataProcessor {

    /**
     * Reads unpacked item JSON and writes the processed item resource.
     * {@code args} is ignored, and failures propagate through Lombok's checked
     * exception bridge. Translation keys are always prefixed, so already
     * namespaced input would receive the prefix a second time.
     */
    @SneakyThrows
    public static void main(String[] args) {
        var gson = new GsonBuilder().create();

        try (var reader = Files.newBufferedReader(Path.of("data/resources/unpacked/items_raw.json"))) {
            var root = JsonParser.parseReader(reader).getAsJsonObject();
            root.entrySet().forEach(entry -> {
                var obj = entry.getValue().getAsJsonObject();
                if (obj.has("translationKey")) {
                    // Runtime translations use namespaced keys; retain the unpacked
                    // key's spelling after adding the Minecraft namespace expected by item consumers.
                    obj.addProperty("translationKey", "minecraft:" + obj.get("translationKey").getAsString());
                }
            });
            Utils.writeFileWithCRLF(Path.of("data/resources/items.json"), gson.toJson(root));
        }
    }
}
