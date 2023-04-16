package cn.allay.block.attribute;

import cn.allay.block.component.attribute.BlockAttributeComponent;
import cn.allay.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.block.component.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.registry.RegistryLoader;
import cn.allay.registry.SimpleMappedRegistry;
import cn.allay.utils.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
@Slf4j
public final class AllayVanillaBlockAttributeRegistry extends SimpleMappedRegistry<VanillaBlockId, BlockAttributeComponentImpl, Map<VanillaBlockId, BlockAttributeComponentImpl>> implements VanillaBlockAttributeRegistry {
    public AllayVanillaBlockAttributeRegistry(RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, BlockAttributeComponentImpl>> loader) {
        super(VanillaBlockId.values(), loader);
    }

    public static class Loader implements RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, BlockAttributeComponentImpl>> {

        @SneakyThrows
        @Override
        public Map<VanillaBlockId, BlockAttributeComponentImpl> load(VanillaBlockId[] input) {
            log.info("Start loading vanilla block attribute data registry...");
            try (var reader = getReader()) {
                var element = JsonParser.parseReader(reader);
                var loaded = new EnumMap<VanillaBlockId, BlockAttributeComponentImpl>(VanillaBlockId.class);
                for (JsonElement jsonElement : element.getAsJsonArray()) {
                    VanillaBlockId type;
                    try {
                        type = VanillaBlockId.valueOf(StringUtils.fastTwoPartSplit(jsonElement.getAsJsonObject().get("identifier").getAsString(), ":", "")[1].toUpperCase());
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown block name: " + jsonElement.getAsJsonObject().get("identifier"));
                        continue;
                    }
                    var component = BlockAttributeComponentImpl.of(jsonElement.toString());
                    loaded.put(type, component);
                }
                int missings = 0;
                for (var vanillaBlock : VanillaBlockId.values()) {
                    if (!loaded.containsKey(vanillaBlock)) {
                        log.error("Missing attribute data for block: " + vanillaBlock);
                        missings++;
                    }
                }
                if (missings != 0) log.error(missings + " blocks' attributes are missing!");
                log.info("Loaded vanilla block attribute data registry successfully");
                return loaded;
            }
        }

        protected Reader getReader() {
            return new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("block_attributes.json"), "block_attributes.json is missing!")));
        }
    }
}
