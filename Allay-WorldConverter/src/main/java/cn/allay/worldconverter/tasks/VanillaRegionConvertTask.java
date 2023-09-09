package cn.allay.worldconverter.tasks;

import cn.allay.api.block.interfaces.BlockAirBehavior;
import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.mapping.Mapping;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.api.world.heightmap.HeightMap;
import cn.allay.api.world.storage.WorldStorageException;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import cn.allay.server.world.storage.rocksdb.RocksDBWorldStorage;
import cn.allay.worldconverter.utils.ConsoleMultiProgressBarConsumer;
import cn.allay.worldconverter.utils.MappingUtils;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jglrxavpok.hephaistos.mca.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Allay Project 8/8/2023
 *
 * @author Cool_Loong
 */
public class VanillaRegionConvertTask extends RecursiveAction {
    static final AtomicBoolean FIRST = new AtomicBoolean(true);
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
        try (ProgressBar pb = new ProgressBarBuilder()
                .setStyle(ProgressBarStyle.ASCII)
                .setInitialMax(1024)
                .setTaskName("Region " + srcRegion.getRegionX() + "," + srcRegion.getRegionZ() + ":")
                .setConsumer(new ConsoleMultiProgressBarConsumer(System.out, FIRST.getAndSet(false)))
                .build()
        ) {
            for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    int cx = i + (srcRegion.getRegionX() << 5), cz = j + (srcRegion.getRegionZ() << 5);
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
                                .state(UnsafeChunk.STATE_FINISHED)
                                .entities(new Long2ObjectNonBlockingMap<>());
                        try {
                            cn.allay.api.world.chunk.ChunkSection[] sections = new cn.allay.api.world.chunk.ChunkSection[dimensionInfo.chunkSectionSize()];
                            for (int sectionY = dimensionInfo.minSectionY(); sectionY < dimensionInfo.maxSectionY(); sectionY++) {
                                int positiveY = sectionY - dimensionInfo.minSectionY();
                                ChunkSection section = chunk.getSection((byte) sectionY);
                                if (section.getEmpty()) break;
                                cn.allay.api.world.chunk.ChunkSection allaySection = new cn.allay.api.world.chunk.ChunkSection(positiveY);
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
                                            if (beBlockStateHash == null) System.out.println(jeBlockState);
                                            Integer beBiomeId = Mapping.getBeBiomeId(section.getBiome(x, y, z));
                                            cn.allay.api.block.type.BlockState blockState = beBlockStateHash == null ? BlockAirBehavior.AIR_TYPE.getDefaultState() : BlockStateHashPalette.getRegistry().get(beBlockStateHash);
                                            BiomeType biomeType = beBiomeId == null ? VanillaBiomeId.values()[1] : VanillaBiomeId.fromId(beBiomeId);
                                            allaySection.setBlockState(x, y, z, 0, blockState);
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
                            e.printStackTrace();
                        }
                        try {
                            AllayChunk allayChunk = new AllayChunk(builder.build());
                            allTask.add(storage.writeChunk(allayChunk).thenRun(pb::step));
                        } catch (WorldStorageException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        pb.step();
                    }
                }
            }
            CompletableFuture.allOf(allTask.toArray(CompletableFuture[]::new)).join();
        }
    }
}
