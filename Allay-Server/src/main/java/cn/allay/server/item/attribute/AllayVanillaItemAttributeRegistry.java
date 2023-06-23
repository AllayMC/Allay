package cn.allay.server.item.attribute;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.component.impl.attribute.ItemAttributes;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/20 <br>
 * Allay Project <br>
 */
@Slf4j
public class AllayVanillaItemAttributeRegistry extends SimpleMappedRegistry<VanillaItemId, ItemAttributes, Map<VanillaItemId, ItemAttributes>> implements VanillaItemAttributeRegistry {
    public AllayVanillaItemAttributeRegistry(RegistryLoader<Void, Map<VanillaItemId, ItemAttributes>> registryLoader) {
        super(null, registryLoader);
    }

    public static class Loader implements RegistryLoader<Void, Map<VanillaItemId, ItemAttributes>> {
        @SneakyThrows
        @Override
        public Map<VanillaItemId, ItemAttributes> load(Void input) {
            log.info("Start loading vanilla item attribute data registry...");
            try (var reader = getReader()) {
                var element = JsonParser.parseReader(reader);
                var loaded = new HashMap<VanillaItemId, ItemAttributes>();
                for (JsonElement jsonElement : element.getAsJsonArray()) {
                    VanillaItemId type;
                    try {
                        var typeName = StringUtils.fastTwoPartSplit(jsonElement.getAsJsonObject().get("name").getAsString(), ":", "")[1]
                                .replace(".","_").toUpperCase();
                        type = VanillaItemId.valueOf(typeName);
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown item name: " + jsonElement.getAsJsonObject().get("name"));
                        continue;
                    }
                    var itemAttributes = ItemAttributes.of(jsonElement.toString());
                    loaded.put(type, itemAttributes);
                }
                log.info("Loaded vanilla item attribute data registry successfully");
                return loaded;
            }
        }

        protected Reader getReader() {
            return new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(
                                    AllayVanillaItemAttributeRegistry.class
                                            .getClassLoader()
                                            .getResourceAsStream("item_data.json"),
                                    "item_data.json is missing!")
                    )
            );

        }
    }
}
