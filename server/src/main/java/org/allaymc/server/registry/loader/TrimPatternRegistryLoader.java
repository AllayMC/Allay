package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.TrimPattern;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IWareQ
 */
@Slf4j
public class TrimPatternRegistryLoader implements RegistryLoader<Void, Map<String, TrimPattern>> {
    @Override
    public Map<String, TrimPattern> load(Void unused) {
        try (var stream = Utils.getResource("trim_data.json")) {
            var map = new HashMap<String, TrimPattern>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("patterns").asList()) {
                var obj = element.getAsJsonObject();
                var itemType = Registries.ITEMS.get(new Identifier(obj.get("itemName").getAsString()));
                var patternId = obj.get("patternId").getAsString();
                map.put(patternId, new TrimPattern(itemType, patternId));
            }

            return map;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
