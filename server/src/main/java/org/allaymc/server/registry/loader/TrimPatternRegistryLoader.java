package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.Utils;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;

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
            Map<String, TrimPattern> result = new HashMap<>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("patterns").asList()) {
                var pattern = element.getAsJsonObject();
                var itemName = pattern.get("itemName").getAsString();
                result.put(itemName, new TrimPattern(itemName, pattern.get("patternId").getAsString()));
            }

            return result;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
