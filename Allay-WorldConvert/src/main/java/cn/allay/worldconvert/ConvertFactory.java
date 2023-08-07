package cn.allay.worldconvert;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.mapping.Mapping;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.worldconvert.utils.MappingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jglrxavpok.hephaistos.mca.AnvilException;
import org.jglrxavpok.hephaistos.mca.BlockState;
import org.jglrxavpok.hephaistos.mca.ChunkColumn;
import org.jglrxavpok.hephaistos.mca.RegionFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@Slf4j
public class ConvertFactory {
    private static final ForkJoinPool THREAD_POOL = new ForkJoinPool();

    public static void of(Path input, Path output, Dimension dimension) {
        input = switch (dimension) {
            case OVERWORLD -> input.resolve("region");
            case NETHER -> input.resolve("DIM1").resolve("region");
            case THE_END -> input.resolve("DIM-1").resolve("region");
        };
        var files = input.toFile();
        if (!files.exists()) {
            log.error("The region folder does not exist!");
            WorldConverter.close(1);
        }
        try {
            File target = output.resolve("region").toFile();
            FileUtils.copyDirectory(files, target);
            for (var file : Objects.requireNonNull(target.listFiles())) {
                try {
                    String name = file.getName();
                    String[] split = name.split("\\.");
                    RegionFile region = new RegionFile(new RandomAccessFile(file, "rw"), Integer.parseInt(split[1]), Integer.parseInt(split[2]), dimension.getDimensionInfo().minHeight(), dimension.getDimensionInfo().maxHeight());
                    THREAD_POOL.submit(new RegionConvertTask(region, 1023));
                } catch (AnvilException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class RegionConvertTask extends RecursiveAction {
        final RegionFile regionFile;
        int index;
        int x;
        int z;

        public RegionConvertTask(RegionFile regionFile, int index) {
            this.regionFile = regionFile;
            this.index = index;
            this.x = index >>> 5;
            this.z = index & 0x0000001F;
        }

        @Override
        protected void compute() {
            if (index > 0) {
                if (index == 1) {
                    invokeAll(new RegionConvertTask(regionFile, --index));
                } else {
                    invokeAll(new RegionConvertTask(regionFile, --index), new RegionConvertTask(regionFile, --index));
                }
            }
            try {
                ChunkColumn chunk = regionFile.getChunk(x, z);
                if (chunk != null && chunk.getGenerationStatus() == ChunkColumn.GenerationStatus.Full) {
                    LevelDBPalette<Integer> statePalette = new LevelDBPalette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState().blockStateHash());
                    LevelDBPalette<Integer> biomePalette = new LevelDBPalette<>(VanillaBiomeId.PLAINS.getId());
                    for (int y = regionFile.getMinY(); y <= regionFile.getMaxY(); ++y) {
                        for (int x = 0; x < 16; ++x) {
                            for (int z = 0; z < 16; ++z) {
                                BlockState blockState = chunk.getBlockState(x, y, z);
                                Integer beBlockStateHash = Mapping.getBeBlockStateHash(MappingUtils.convertBlockState(blockState));
                                Integer beBiomeId = Mapping.getBeBiomeId(chunk.getBiome(x, y, z));
                                int biomeId = beBiomeId == null ? 1 : beBiomeId;
                                int blockStateHash = beBlockStateHash == null ? VanillaBlockTypes.AIR_TYPE.getDefaultState().blockStateHash() : beBlockStateHash;
                                int index = Chunk.index(x, y, z);
                                biomePalette.set(index, biomeId);
                                statePalette.set(index, blockStateHash);
                            }
                        }
                    }
                }
            } catch (AnvilException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
