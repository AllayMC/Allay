package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code TrimMaterialRegistryLoader} reads armor trim materials from bundled trim data and
 * indexes them by their string identifiers. The current item registry resolves each associated
 * item, while the resource supplies the material identity and color retained in the resulting trim
 * values.
 *
 * @author IWareQ
 */
@Slf4j
public class TrimMaterialRegistryLoader implements RegistryLoader<Void, Map<String, TrimMaterial>> {
    /**
     * Loads {@code "materials"} from {@code "trim_data.json"} into a mutable string-keyed
     * map. Item registration must precede this load; unresolved item lookups are retained as
     * {@code null}, and resource or conversion failures become {@code AssertionError}.
     */
    @Override
    public Map<String, TrimMaterial> load(Void unused) {
        try (var stream = Utils.getResource("trim_data.json")) {
            var map = new HashMap<String, TrimMaterial>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("materials").asList()) {
                var obj = element.getAsJsonObject();
                var itemType = Registries.ITEMS.get(new Identifier(obj.get("itemName").getAsString()));
                var materialId = obj.get("materialId").getAsString();
                var color = obj.get("color").getAsString();
                map.put(materialId, new TrimMaterial(itemType, materialId, color));
            }

            return map;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
