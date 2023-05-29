package cn.allay.server.block.palette;

import cn.allay.api.block.palette.BlockPaletteDataEntry;
import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.block.property.BlockPropertyTypeRegistry;
import cn.allay.api.block.property.BlockState;
import cn.allay.api.block.property.type.BlockPropertyType;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
@Slf4j
public class AllayBlockPaletteRegistry extends SimpleMappedRegistry<Integer, BlockPaletteDataEntry, Map<Integer, BlockPaletteDataEntry>> implements BlockPaletteRegistry {
    public AllayBlockPaletteRegistry(RegistryLoader<Void, Map<Integer, BlockPaletteDataEntry>> loader) {
        super(null, loader);
    }

    public static class Loader implements RegistryLoader<Void, Map<Integer, BlockPaletteDataEntry>> {
        @SneakyThrows
        @Override
        public Map<Integer, BlockPaletteDataEntry> load(Void input) {
            log.info("Start loading vanilla block palette registry...");
            List<NbtMap> nbtMap;
            try (var inputStream = getNBTInputStream()) {
                nbtMap = ((NbtMap) inputStream.readTag()).getList("blocks", NbtType.COMPOUND);
            }

            Map<Integer, BlockPaletteDataEntry> loaded = new Int2ObjectOpenHashMap<>();

            for (var blockPalette : nbtMap) {
                var name = new Identifier(blockPalette.getString("name"));
                var version = blockPalette.getInt("version");
                var properties = new ArrayList<BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
                blockPalette.getCompound("states").forEach((k, v) -> {
                    var propertyType = getBlockPropertyTypeRegistry().get(k);
                    assert propertyType != null;
                    properties.add(propertyType.tryCreateValue(v));
                });
                BlockType<?> blockType = BlockTypeRegistry.getRegistry().get(name);
                assert blockType != null;
                BlockState<?> blockState = blockType.ofState(properties);
                loaded.putIfAbsent(blockState.getBlockStateHash(), new BlockPaletteDataEntry(name, blockState, version));
            }
            return loaded;
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