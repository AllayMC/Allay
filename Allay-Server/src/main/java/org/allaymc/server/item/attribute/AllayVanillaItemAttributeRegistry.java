package org.allaymc.server.item.attribute;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemAttributes;
import org.allaymc.api.item.registry.VanillaItemAttributeRegistry;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.AllayStringUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    public static class Loader implements RegistryLoader<Void, Map<VanillaItemId, ItemAttributes>> {

        protected Supplier<InputStream> streamSupplier;

        public Loader(Supplier<InputStream> streamSupplier) {
            this.streamSupplier = streamSupplier;
        }

        public Loader() {
            this(() -> new BufferedInputStream(
                    Objects.requireNonNull(
                            AllayVanillaItemAttributeRegistry.class
                                    .getClassLoader()
                                    .getResourceAsStream("item_data.nbt"),
                            "item_data.nbt is missing!")
            ));
        }

        @SneakyThrows
        @Override
        public Map<VanillaItemId, ItemAttributes> load(Void input) {
            log.info("Start loading vanilla item attribute data registry...");
            try (var reader = NbtUtils.createGZIPReader(streamSupplier.get())) {
                var items = ((NbtMap) reader.readTag()).getList("item", NbtType.COMPOUND);
                var loaded = new HashMap<VanillaItemId, ItemAttributes>();
                for (var dataEntry : items) {
                    VanillaItemId type;
                    try {
                        var typeName = AllayStringUtils.fastTwoPartSplit(dataEntry.getString("name"), ":", "")[1]
                                .replace(".","_").toUpperCase();
                        type = VanillaItemId.valueOf(typeName);
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown item name: " + dataEntry.getString("name"));
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
