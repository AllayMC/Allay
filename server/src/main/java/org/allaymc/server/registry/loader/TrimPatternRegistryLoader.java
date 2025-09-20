package org.allaymc.server.registry.loader;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.utils.JSONUtils;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;

import java.util.HashSet;
import java.util.Set;

/**
 * @author IWareQ
 */
@Slf4j
public class TrimPatternRegistryLoader implements RegistryLoader<Void, Set<TrimPattern>> {
    @Override
    public Set<TrimPattern> load(Void unused) {
        try (var stream = Utils.getResource("trim_data.json")) {
            Set<TrimPattern> result = new HashSet<>();

            var root = JSONUtils.from(stream, JsonObject.class);
            for (var element : root.getAsJsonArray("patterns").asList()) {
                var pattern = element.getAsJsonObject();
                result.add(new TrimPattern(pattern.get("itemName").getAsString(), pattern.get("patternId").getAsString()));
            }

            return result;
        } catch (Exception e) {
            throw new AssertionError("Failed to load trim_data.json", e);
        }
    }
}
