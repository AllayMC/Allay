package cn.allay.server.block.palette;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.palette.VanillaBlockStatePalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.api.utils.HashUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/7 <br>
 * Allay Project <br>
 */
@Deprecated
@Slf4j
public final class AllayVanillaBlockStatePalette extends SimpleMappedRegistry<BlockState, Integer, Map<BlockState, Integer>> implements VanillaBlockStatePalette {
    public AllayVanillaBlockStatePalette(RegistryLoader<Void, Map<BlockState, Integer>> loader) {
        super(null, loader);
    }

    //TODO: Using block_palette.json instead
    public static class Loader implements RegistryLoader<Void, Map<BlockState, Integer>> {

        @SneakyThrows
        @Override
        public Map<BlockState, Integer> load(Void input) {
            log.info("Start loading vanilla block palette registry...");
            var runtimeCounter = new AtomicInteger(0);
            List<NbtMap> nbtMap;
            try (var inputStream = getNBTInputStream()) {
                nbtMap = ((NbtMap) inputStream.readTag()).getList("blocks", NbtType.COMPOUND);
            }
            var loaded = new HashMap<BlockState, Integer>();
            for (var blockState : nbtMap) {
                var runtimeId = runtimeCounter.getAndIncrement();
                var tag = NbtMap.builder()
                        .putString("name", blockState.getString("name"))
                        .putCompound("states", blockState.getCompound("states"))
                        .build();
                var hash = HashUtils.fnv1a_32_nbt(tag);
                loaded.put(BlockStateHashPalette.getRegistry().get(hash), runtimeId);
            }
            log.info("Loaded vanilla block palette data registry successfully");
            return loaded;
        }

        @SneakyThrows
        protected NBTInputStream getNBTInputStream() {
            var input = AllayVanillaBlockStatePalette.class.getClassLoader().getResourceAsStream("block_palette.nbt");
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
    }
}
