package org.allaymc.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author daoge_cmd
 */
public class ItemDataProcessor {

    @SneakyThrows
    public static void main(String[] args) {
        var gson = new GsonBuilder().create();

        try (var reader = Files.newBufferedReader(Path.of("data/resources/unpacked/items_raw.json"))) {
            var root = JsonParser.parseReader(reader).getAsJsonObject();
            root.entrySet().forEach(entry -> {
                var obj = entry.getValue().getAsJsonObject();
                if (obj.has("translationKey")) {
                    obj.addProperty("translationKey", "minecraft:" + obj.get("translationKey").getAsString());
                }
            });
            Utils.writeFileWithCRLF(Path.of("data/resources/items.json"), gson.toJson(root));
        }
    }
}
