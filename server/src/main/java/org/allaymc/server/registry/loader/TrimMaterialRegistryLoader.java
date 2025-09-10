package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.utils.JSONUtils;
import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IWareQ
 */
@Slf4j
public class TrimMaterialRegistryLoader implements RegistryLoader<Void, Set<TrimMaterial>> {
    @Override
    public Set<TrimMaterial> load(Void unused) {
        try (var stream = Utils.getResource("trim_data.json")) {
            Set<TrimMaterial> result = new HashSet<>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("materials").asList()) {
                var material = element.getAsJsonObject();
                result.add(new TrimMaterial(
                        material.get("materialId").getAsString(),
                        material.get("color").getAsString(),
                        material.get("itemName").getAsString()
                ));
            }

            return result;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
