package cn.allay.worldconverter.tasks;

import cn.allay.api.block.interfaces.BlockWaterBehavior;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.mapping.Mapping;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.ChunkState;
import cn.allay.api.world.heightmap.HeightMap;
import cn.allay.api.world.storage.WorldStorageException;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import cn.allay.server.world.storage.rocksdb.RocksDBWorldStorage;
import cn.allay.worldconverter.utils.ConsoleMultiProgressBarConsumer;
import cn.allay.worldconverter.utils.MappingUtils;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jglrxavpok.hephaistos.mca.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Allay Project 8/8/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class VanillaRegionConvertTask extends RecursiveAction {
    static final AtomicBoolean FIRST = new AtomicBoolean(true);
    static final int AIR_BLOCK_HASH = -604749536;
    final RegionFile srcRegion;
    final RocksDBWorldStorage storage;
    final DimensionInfo dimensionInfo;
    final Set<CompletableFuture<?>> allTask = new HashSet<>();

    public VanillaRegionConvertTask(RegionFile srcRegion, RocksDBWorldStorage storage, DimensionInfo dimensionInfo) {
        this.srcRegion = srcRegion;
        this.storage = storage;
        this.dimensionInfo = dimensionInfo;
    }

    @Override
    protected void compute() {
        try (ProgressBar pb = ProgressBar
                .builder()
                .setStyle(ProgressBarStyle.ASCII)
                .setInitialMax(1024)
                .setTaskName("Region " + srcRegion.getRegionX() + "," + srcRegion.getRegionZ() + ":")
                .setConsumer(new ConsoleMultiProgressBarConsumer(System.out, FIRST.getAndSet(false)))
                .build()
        ) {
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    int cx = i + (srcRegion.getRegionX() * 32), cz = j + (srcRegion.getRegionZ() * 32);
                    ChunkColumn chunk;
                    try {
                        chunk = srcRegion.getChunk(cx, cz);
                    } catch (AnvilException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (chunk != null) {
                        var builder = AllayUnsafeChunk.builder().chunkX(cx).chunkZ(cz).dimensionInfo(dimensionInfo);
                        Heightmap heightMap = chunk.getWorldSurfaceHeightMap();
                        HeightMap allayHeightMap = new HeightMap();
                        for (int h1 = 0; h1 < 16; h1++) {
                            for (int h2 = 0; h2 < 16; h2++) {
                                allayHeightMap.set(h1, h2, (short) heightMap.get(h1, h2));
                            }
                        }
                        builder.heightMap(allayHeightMap)
                                .state(ChunkState.FINISHED)
                                .entities(new Long2ObjectNonBlockingMap<>());
                        try {
                            int minY = srcRegion.getMinY() >> 4;
                            int maxY = srcRegion.getMaxY() >> 4;
                            cn.allay.api.world.chunk.ChunkSection[] sections = new cn.allay.api.world.chunk.ChunkSection[dimensionInfo.chunkSectionSize()];
                            for (int sectionY = minY; sectionY <= maxY; sectionY++) {
                                ChunkSection section = chunk.getSection((byte) sectionY);
                                int positiveY = sectionY - minY;
                                cn.allay.api.world.chunk.ChunkSection allaySection = new cn.allay.api.world.chunk.ChunkSection(positiveY);
                                if (section.getEmpty()) {
                                    log.warn("section" + positiveY + " is empty");
                                    sections[positiveY] = allaySection;
                                    continue;
                                }
                                for (int x = 0; x < 16; ++x) {
                                    for (int y = 0; y < 16; ++y) {
                                        for (int z = 0; z < 16; ++z) {
                                            BlockState jeBlockState;
                                            try {
                                                jeBlockState = section.get(x, y, z);
                                            } catch (AnvilException e) {
                                                throw new RuntimeException(e);
                                            }

                                            Integer beBlockStateHash = Mapping.getBeBlockStateHash(MappingUtils.convertBlockState(jeBlockState));
                                            if (beBlockStateHash == null) {
                                                log.warn("unmapping blockstate:" + jeBlockState);
                                                beBlockStateHash = AIR_BLOCK_HASH;
                                            }
                                            cn.allay.api.block.type.BlockState blockState = BlockStateHashPalette.getRegistry().get(beBlockStateHash);
                                            allaySection.setBlockState(x, y, z, 0, blockState);
                                            Map<String, String> properties = jeBlockState.getProperties();
                                            switch (jeBlockState.getName()) {
                                                //Specially water block
                                                case "minecraft:kelp", "minecraft:seagrass", "minecraft:bubble_column" ->
                                                        allaySection.setBlockState(x, y, z, 1, BlockWaterBehavior.WATER_TYPE.getDefaultState());
                                            }
                                            //common water block
                                            if (properties.containsKey("waterlogged") && properties.get("waterlogged").equals("true")) {
                                                allaySection.setBlockState(x, y, z, 1, BlockWaterBehavior.WATER_TYPE.getDefaultState());
                                            }

                                            String jeBiomeId = section.getBiome(x, y, z);
                                            Integer beBiomeId = Mapping.getBeBiomeId(jeBiomeId);
                                            if (beBiomeId == null) {
                                                log.warn("unmapping biome:" + jeBiomeId);
                                                beBiomeId = 1;
                                            }
                                            BiomeType biomeType = VanillaBiomeId.fromId(beBiomeId);
                                            allaySection.setBiomeType(x, y, z, biomeType);
                                        }
                                    }
                                }
                                byte[] blockLights = section.getBlockLights();
                                if (blockLights.length == 2048) {
                                    allaySection.blockLights().copyFrom(blockLights);
                                }
                                byte[] skyLights = section.getSkyLights();
                                if (skyLights.length == 2048) {
                                    allaySection.skyLights().copyFrom(skyLights);
                                }
                                sections[positiveY] = allaySection;
                            }
                            builder.sections(sections);
                        } catch (Exception e) {
                            log.error("convert error:", e);
                        }
                        try {
                            AllayChunk allayChunk = new AllayChunk(builder.build());
                            allTask.add(storage.writeChunk(allayChunk).thenRun(pb::step));
                        } catch (WorldStorageException e) {
                            throw new RuntimeException(e);
                        }
                        srcRegion.forget(chunk);
                    } else {
                        pb.step();
                    }
                }
            }
            CompletableFuture.allOf(allTask.toArray(CompletableFuture[]::new)).join();
        }
    }
}
