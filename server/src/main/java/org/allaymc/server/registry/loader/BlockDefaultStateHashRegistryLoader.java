package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class BlockDefaultStateHashRegistryLoader implements RegistryLoader<Void, Map<BlockId, Integer>> {
    @SneakyThrows
    @Override
    public Map<BlockId, Integer> load(Void unused) {
        var map = new EnumMap<BlockId, Integer>(BlockId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_types.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = BlockId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                map.put(id, (int) obj.get("defaultBlockStateHash").getAsLong());
            });
        }

        return map;
    }
}
