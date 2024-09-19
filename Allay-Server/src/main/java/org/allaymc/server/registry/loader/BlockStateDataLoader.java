package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Slf4j
public class BlockStateDataLoader implements RegistryLoader<Void, Map<BlockId, Map<Integer, BlockStateData>>> {
    @Override
    @SneakyThrows
    public Map<BlockId, Map<Integer, BlockStateData>> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_states.json")))) {
            var loaded = new EnumMap<BlockId, Map<Integer, BlockStateData>>(BlockId.class);
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var obj = entry.getAsJsonObject();
                var type = BlockId.fromIdentifier(new Identifier(obj.get("name").getAsString()));
                if (type == null) {
                    log.warn("Unknown block id: {}", obj.get("name").getAsString());
                    return;
                }

                var blockStateData = BlockStateData.fromJson(obj.toString());
                if (!loaded.containsKey(type))
                    loaded.put(type, new Int2ObjectOpenHashMap<>());

                loaded.get(type).put(obj.get("blockStateHash").getAsInt(), blockStateData);
            });
            return loaded;
        }
    }
}
