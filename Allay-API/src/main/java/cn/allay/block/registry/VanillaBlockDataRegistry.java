package cn.allay.block.registry;

import cn.allay.block.component.BlockDataComponent;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.registry.RegistryLoader;
import cn.allay.registry.SimpleMappedRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
@Slf4j
public final class VanillaBlockDataRegistry extends SimpleMappedRegistry<VanillaBlockId, BlockDataComponent, Map<VanillaBlockId, BlockDataComponent>> {

    private static VanillaBlockDataRegistry INSTANCE;

    private VanillaBlockDataRegistry(RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, BlockDataComponent>> loader) {
        super(VanillaBlockId.values(), loader);
    }

    public static VanillaBlockDataRegistry getInstance() {
        return INSTANCE;
    }

    public static void init(RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, BlockDataComponent>> loader) {
        INSTANCE = new VanillaBlockDataRegistry(loader);
    }

    public static class Loader implements RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, BlockDataComponent>> {


        @SneakyThrows
        @Override
        public Map<VanillaBlockId, BlockDataComponent> load(VanillaBlockId[] input) {
            log.info("Start loading vanilla block palette registry");
            try (var reader = getReader()) {
                var element = JsonParser.parseReader(reader);
                var loaded = new HashMap<VanillaBlockId, BlockDataComponent>();
                for (JsonElement jsonElement : element.getAsJsonArray()) {
                    VanillaBlockId type;
                    try {
                        type = VanillaBlockId.valueOf(jsonElement.getAsJsonObject().get("name").getAsString());
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown block name: " + jsonElement.getAsJsonObject().get("name"));
                        continue;
                    }
                    var component = BlockDataComponent.of(jsonElement.toString());
                    loaded.put(type, component);
                }
                for (var vanillaBlock : VanillaBlockId.values()) {
                    if (!loaded.containsKey(vanillaBlock)) {
                        log.error("Missing block attributes for block: " + vanillaBlock);
                    }
                }
                return loaded;
            }
        }

        protected Reader getReader() {
            return new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("block_attributes.json")));
        }
    }
}
