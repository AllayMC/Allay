package cn.allay.dataext;

import cn.allay.dependence.VanillaEntityId;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class VanillaEntityDataExtractor {

    public static final Gson GSON = new Gson();
    public static final Path ENTITY_FILE_PATH = BedrockSampleDataExtractor.BEHAVIOR_PACK_ROOT_PATH.resolve("entities");
    public static final Path FILE_OUT_PUT_PATH = Path.of("Data/unpacked/entity_attributes.json");

    //TODO: Temporarily not working. Make it work
    @SneakyThrows
    static void extractDataFromBedrockSample() {
        var datas = new TreeMap<String, EntityDataEntry>();
        for (var entityId : VanillaEntityId.values()) {
            var filePath = ENTITY_FILE_PATH.resolve(resolveFileName(entityId));
            if (!Files.exists(filePath)) {
                System.out.println("Not found: " + filePath);
                continue;
            }
            System.out.println("Found: " + filePath);
            var reader = JsonParser.parseReader(Files.newBufferedReader(filePath));

            //AABB
            var collisionBoxObj = reader.getAsJsonObject()
                    .getAsJsonObject("minecraft:entity")
                    .getAsJsonObject("components")
                    .getAsJsonObject("minecraft:collision_box");
            var width = collisionBoxObj.get("width").getAsDouble();
            var height = collisionBoxObj.get("height").getAsDouble();
            var aabb = -width / 2 + ",0," + -width / 2 + "," + width / 2 + "," + height + "," + width / 2;

            //Physics
            var physicsObj = reader.getAsJsonObject()
                    .getAsJsonObject("minecraft:entity")
                    .getAsJsonObject("components")
                    .getAsJsonObject("minecraft:physics");
            var hasCollision = !physicsObj.has("has_collision") || physicsObj.get("has_collision").getAsBoolean();

            //Health
            var healthObj = reader.getAsJsonObject()
                    .getAsJsonObject("minecraft:entity")
                    .getAsJsonObject("components")
                    .getAsJsonObject("minecraft:health");
            var maxHealth = healthObj != null ?
                    healthObj.has("max") ?
                            healthObj.get("max").getAsInt() :
                                    healthObj.get("value").isJsonObject() ?
                                            healthObj.getAsJsonObject("value").get("range_max").getAsInt() :
                                            healthObj.get("value").getAsInt() : Integer.MAX_VALUE;

            var dataEntry = new EntityDataEntry(aabb, hasCollision, maxHealth);
            datas.put(entityId.getIdentifier().toString(), dataEntry);
        }

        var output = GSON.toJson(datas);
        Files.writeString(FILE_OUT_PUT_PATH, output);
    }

    static String resolveFileName(VanillaEntityId entityId) {
        return switch (entityId) {
            case COD -> "fish.json";
            default -> entityId.getIdentifier().path() + ".json";
        };
    }

    record EntityDataEntry(
            String aabb,
            boolean hasCollision,
            int maxHealth
    ) {
    }
}
