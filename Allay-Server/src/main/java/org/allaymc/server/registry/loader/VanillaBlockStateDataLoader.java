package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class VanillaBlockStateDataLoader implements RegistryLoader<Void, Map<VanillaBlockId, Map<Integer, BlockStateData>>> {
    @Override
    @SneakyThrows
    public Map<VanillaBlockId, Map<Integer, BlockStateData>> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_states.json")))) {
            var loaded = new EnumMap<VanillaBlockId, Map<Integer, BlockStateData>>(VanillaBlockId.class);
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var obj = entry.getAsJsonObject();
                var type = VanillaBlockId.fromIdentifier(new Identifier(obj.get("name").getAsString()));
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
