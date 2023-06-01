package cn.allay.server.block.type;

import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.block.property.BlockPropertyTypeRegistry;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.utils.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.DataInputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
@Slf4j
public class AllayBlockPaletteRegistry extends SimpleMappedRegistry<Integer, BlockState, Map<Integer, BlockState>> implements BlockPaletteRegistry {
    public AllayBlockPaletteRegistry(RegistryLoader<Void, Map<Integer, BlockState>> loader) {
        super(null, loader);
    }

    public static class Loader implements RegistryLoader<Void, Map<Integer, BlockState>> {
        @SneakyThrows
        @Override
        public Map<Integer, BlockState> load(Void input) {
            log.info("Start loading vanilla block palette registry...");
            List<NbtMap> nbtMap;
            try (var inputStream = getNBTInputStream()) {
                nbtMap = ((NbtMap) inputStream.readTag()).getList("blocks", NbtType.COMPOUND);
            }
            Set<String> firstState = new HashSet<>();
            Map<Integer, BlockState> result = new ConcurrentHashMap<>();
            for (var blockPalette : nbtMap) {
                var name = blockPalette.getString("name");
                VanillaBlockId blockId;
                try {
                    blockId = VanillaBlockId.valueOf(StringUtils.fastTwoPartSplit(name, ":", "")[1].toUpperCase());
                } catch (IllegalArgumentException ignore) {
                    log.error("Unknown block name: " + name);
                    continue;
                }
                AllayBlockType<?> blockType = (AllayBlockType<?>) blockId.getBlockType();
                var properties = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
                blockPalette.getCompound("states").forEach((k, v) -> {
                    var propertyType = getBlockPropertyTypeRegistry().get(k);
                    assert propertyType != null;
                    properties.add(propertyType.tryCreateValue(v));
                });
                int hash = HashUtils.computeBlockStateHash(blockType.getIdentifier(), properties);
                AllayBlockState state = new AllayBlockState(blockType, properties, hash);
                result.putIfAbsent(hash, state);
                if (!firstState.contains(name)) {
                    blockType.setDefaultState(state);
                    firstState.add(name);
                }
                blockType.getUnSafeAllStates().add(state);
            }
            log.info("Loaded vanilla block palette data registry successfully");
            return result;
        }

        @SneakyThrows
        protected NBTInputStream getNBTInputStream() {
            var input = AllayBlockPaletteRegistry.class.getClassLoader().getResourceAsStream("block_palette.nbt");
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