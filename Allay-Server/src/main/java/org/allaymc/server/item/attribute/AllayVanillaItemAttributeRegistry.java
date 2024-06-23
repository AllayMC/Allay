package org.allaymc.server.item.attribute;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemAttributes;
import org.allaymc.api.item.registry.VanillaItemAttributeRegistry;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.utils.ResourceUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayVanillaItemAttributeRegistry extends SimpleMappedRegistry<VanillaItemId, ItemAttributes, Map<VanillaItemId, ItemAttributes>> implements VanillaItemAttributeRegistry {
    public AllayVanillaItemAttributeRegistry(RegistryLoader<Void, Map<VanillaItemId, ItemAttributes>> registryLoader) {
        super(null, registryLoader);
    }

    @AllArgsConstructor
    public static class Loader implements RegistryLoader<Void, Map<VanillaItemId, ItemAttributes>> {
        protected Supplier<InputStream> streamSupplier;

        public Loader() {
            this(() -> new BufferedInputStream(ResourceUtils.getResource("item_data.nbt")));
        }

        @SneakyThrows
        @Override
        public Map<VanillaItemId, ItemAttributes> load(Void input) {
            log.info("Start loading vanilla item attribute data registry...");
            try (var reader = NbtUtils.createGZIPReader(streamSupplier.get())) {
                var items = ((NbtMap) reader.readTag()).getList("item", NbtType.COMPOUND);
                var loaded = new ConcurrentHashMap<VanillaItemId, ItemAttributes>();
                for (var dataEntry : items) {
                    VanillaItemId type;
                    try {
                        var typeName = AllayStringUtils.fastTwoPartSplit(dataEntry.getString("name"), ":", "")[1]
                                .replace(".", "_").toUpperCase();
                        type = VanillaItemId.valueOf(typeName);
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown item name: {}", dataEntry.getString("name"));
                        continue;
                    }
                    var itemAttributes = ItemAttributes.fromNBT(dataEntry);
                    loaded.put(type, itemAttributes);
                }
                log.info("Loaded vanilla item attribute data registry successfully");
                return loaded;
            }
        }
    }
}
