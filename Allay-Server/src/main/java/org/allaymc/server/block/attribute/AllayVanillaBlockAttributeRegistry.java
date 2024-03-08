package org.allaymc.server.block.attribute;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.registry.VanillaBlockAttributeRegistry;
import org.allaymc.api.common.utils.AllayStringUtils;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayVanillaBlockAttributeRegistry extends SimpleMappedRegistry<VanillaBlockId, Map<Integer, BlockAttributes>, Map<VanillaBlockId, Map<Integer, BlockAttributes>>> implements VanillaBlockAttributeRegistry {
    public AllayVanillaBlockAttributeRegistry(RegistryLoader<Void, Map<VanillaBlockId, Map<Integer, BlockAttributes>>> loader) {
        super(null, loader);
    }

    public static class Loader implements RegistryLoader<Void, Map<VanillaBlockId, Map<Integer, BlockAttributes>>> {

        protected Supplier<InputStream> streamSupplier;

        public Loader(Supplier<InputStream> streamSupplier) {
            this.streamSupplier = streamSupplier;
        }

        public Loader() {
            this(() -> new BufferedInputStream(
                    Objects.requireNonNull(
                            AllayVanillaBlockAttributeRegistry.class
                                    .getClassLoader()
                                    .getResourceAsStream("block_attributes.nbt"),
                            "block_attributes.nbt is missing!")
            ));
        }

        @SneakyThrows
        @Override
        public Map<VanillaBlockId, Map<Integer, BlockAttributes>> load(Void input) {
            log.info("Start loading vanilla block attribute data registry...");
            try (var reader = NbtUtils.createGZIPReader(streamSupplier.get())) {
                var blocks = ((NbtMap) reader.readTag()).getList("block", NbtType.COMPOUND);
                // No need to use ConcurrentHashMap as it won't be modified after loading
                var loaded = new EnumMap<VanillaBlockId, Map<Integer, BlockAttributes>>(VanillaBlockId.class);
                for (var dataEntry : blocks) {
                    VanillaBlockId type;
                    try {
                        type = VanillaBlockId.valueOf(AllayStringUtils.fastTwoPartSplit(dataEntry.getString("name"), ":", "")[1].toUpperCase());
                    } catch (IllegalArgumentException ignore) {
                        log.error("Unknown block name: " + dataEntry.getString("name"));
                        continue;
                    }
                    var blockAttributes = BlockAttributes.fromNBT(dataEntry);
                    if (!loaded.containsKey(type))
                        loaded.put(type, new Int2ObjectOpenHashMap<>());
                    loaded.get(type).put(dataEntry.getInt("blockStateHash"), blockAttributes);
                }
                log.info("Loaded vanilla block attribute data registry successfully");
                return loaded;
            }
        }
    }
}
