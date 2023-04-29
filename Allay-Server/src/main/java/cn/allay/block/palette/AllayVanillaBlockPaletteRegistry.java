package cn.allay.block.palette;

import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.registry.RegistryLoader;
import cn.allay.registry.SimpleMappedRegistry;
import cn.allay.utils.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.DataInputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/7 <br>
 * Allay Project <br>
 * TODO: Reduce memory usage
 */
@Slf4j
public final class AllayVanillaBlockPaletteRegistry extends SimpleMappedRegistry<VanillaBlockId, VanillaBlockPaletteDataEntry, Map<VanillaBlockId, VanillaBlockPaletteDataEntry>> implements VanillaBlockPaletteRegistry {
    public AllayVanillaBlockPaletteRegistry(RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, VanillaBlockPaletteDataEntry>> loader) {
        super(VanillaBlockId.values(), loader);
    }

    public static class Loader implements RegistryLoader<VanillaBlockId[], Map<VanillaBlockId, VanillaBlockPaletteDataEntry>> {

        @SneakyThrows
        @Override
        public Map<VanillaBlockId, VanillaBlockPaletteDataEntry> load(VanillaBlockId[] input) {
            log.info("Start loading vanilla block palette registry...");
            var runtimeCounter = new AtomicInteger(0);
            List<NbtMap> nbtMap;
            try (var inputStream = getNBTInputStream()) {
                nbtMap = ((NbtMap) inputStream.readTag()).getList("blocks", NbtType.COMPOUND);
            }
            var loaded = new EnumMap<VanillaBlockId, VanillaBlockPaletteDataEntry>(VanillaBlockId.class);
            for (var blockPalette : nbtMap) {
                var runtimeId = runtimeCounter.getAndIncrement();
                var name = blockPalette.getString("name");
                VanillaBlockId blockId;
                try {
                    blockId = VanillaBlockId.valueOf(StringUtils.fastTwoPartSplit(name, ":", "")[1].toUpperCase());
                } catch (IllegalArgumentException ignore) {
                    log.error("Unknown block name: " + name);
                    continue;
                }
                var version = blockPalette.getInt("version");
                var propertyTypes = new ArrayList<BlockPropertyType<?>>();
                var properties = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?>>();
                blockPalette.getCompound("states").forEach((k, v) -> {
                    var propertyType = getBlockPropertyTypeRegistry().get(k);
                    propertyTypes.add(propertyType);
                    properties.add(propertyType.tryCreateValue(v));
                });
                if (!loaded.containsKey(blockId)) {
                    var runtimeIdMap = new HashMap<List<BlockPropertyType.BlockPropertyValue<?, ?>>, Integer>();
                    runtimeIdMap.put(properties, runtimeId);
                    var entry = new VanillaBlockPaletteDataEntry(blockId, propertyTypes, runtimeIdMap, version);
                    loaded.put(blockId, entry);
                } else {
                    loaded.get(blockId).runtimeIdMap().put(properties, runtimeId);
                }
            }
            int missings = 0;
            for (var vanillaBlock : VanillaBlockId.values()) {
                if (!loaded.containsKey(vanillaBlock)) {
                    log.error("Missing palette data for block: " + vanillaBlock);
                    missings++;
                }
            }
            if (missings != 0) log.error(missings + " blocks' palette data are missing!");
            log.info("Loaded vanilla block palette data registry successfully");
            return loaded;
        }

        @SneakyThrows
        protected NBTInputStream getNBTInputStream() {
            var input = AllayVanillaBlockPaletteRegistry.class.getClassLoader().getResourceAsStream("Data/block_palette.nbt");
            if (input == null)
                throw new NullPointerException("block_palette.nbt not found!");
            return new NBTInputStream(
                    new DataInputStream(
                            new GZIPInputStream(
                                    input
                            )
                    )
            );
        }

        protected BlockPropertyTypeRegistry getBlockPropertyTypeRegistry() {
            return BlockPropertyTypeRegistry.getRegistry();
        }
    }
}
