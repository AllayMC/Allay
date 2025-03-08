package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IWareQ
 */
@Slf4j
public class TrimMaterialRegistryLoader implements RegistryLoader<Void, Map<String, TrimMaterial>> {
    @Override
    public Map<String, TrimMaterial> load(Void unused) {
        try (var stream = Utils.getResource("trim_data.json")) {
            Map<String, TrimMaterial> result = new HashMap<>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("materials").asList()) {
                var material = element.getAsJsonObject();
                var itemName = material.get("itemName").getAsString();
                result.put(itemName, new TrimMaterial(
                        material.get("materialId").getAsString(),
                        material.get("color").getAsString(),
                        itemName
                ));
            }

            return result;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
