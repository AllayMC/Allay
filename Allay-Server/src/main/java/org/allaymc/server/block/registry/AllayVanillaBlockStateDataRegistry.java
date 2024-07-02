package org.allaymc.server.block.registry;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.block.registry.VanillaBlockStateDataRegistry;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayVanillaBlockStateDataRegistry extends SimpleMappedRegistry<VanillaBlockId, Map<Integer, BlockStateData>, Map<VanillaBlockId, Map<Integer, BlockStateData>>> implements VanillaBlockStateDataRegistry {
    public AllayVanillaBlockStateDataRegistry(RegistryLoader<Void, Map<VanillaBlockId, Map<Integer, BlockStateData>>> loader) {
        super(null, loader);
    }

    @AllArgsConstructor
    public static class Loader implements RegistryLoader<Void, Map<VanillaBlockId, Map<Integer, BlockStateData>>> {
        protected Supplier<InputStream> streamSupplier;

        public Loader() {
            this(() -> new BufferedInputStream(ResourceUtils.getResource("block_states.json")));
        }

        @SneakyThrows
        @Override
        public Map<VanillaBlockId, Map<Integer, BlockStateData>> load(Void input) {
            log.info("Start loading vanilla block state data registry...");
            try (var reader = new InputStreamReader(streamSupplier.get())) {
                var loaded = new EnumMap<VanillaBlockId, Map<Integer, BlockStateData>>(VanillaBlockId.class);
                JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                    var obj = entry.getAsJsonObject();
                    VanillaBlockId type;
                    try {
                        type = VanillaBlockId.valueOf(AllayStringUtils.fastTwoPartSplit(obj.get("name").getAsString(), ":", "")[1].toUpperCase());
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown block name: {}", obj.get("name").getAsString());
                        return;
                    }
                    var blockStateData = BlockStateData.fromJson(obj.toString());
                    if (!loaded.containsKey(type))
                        loaded.put(type, new Int2ObjectOpenHashMap<>());
                    loaded.get(type).put(obj.get("blockStateHash").getAsInt(), blockStateData);
                });
                log.info("Loaded vanilla block attribute data registry successfully");
                return loaded;
            }
        }
    }
}
