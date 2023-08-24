package cn.allay.worldconvert.tasks;

import cn.allay.worldconvert.utils.ConsoleMultiProgressBarConsumer;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jglrxavpok.hephaistos.mca.RegionFile;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Allay Project 8/8/2023
 *
 * @author Cool_Loong
 */
public class VanillaRegionConvertTask implements Runnable {
    private final static AtomicBoolean FIRST = new AtomicBoolean(true);
    private final static Integer AIR_BLOCK_STATE_HASH = -604749536;
    final RegionFile srcRegion;
    final AnvilRegionFile targetRegion;

    public VanillaRegionConvertTask(RegionFile srcRegion, AnvilRegionFile targetRegion) {
        this.srcRegion = srcRegion;
        this.targetRegion = targetRegion;
    }

    @Override
    public void run() {
        ProgressBarBuilder progressBarBuilder = new ProgressBarBuilder();
        try (ProgressBar pb = progressBarBuilder.setTaskName("region " + srcRegion.getRegionX() + "," + srcRegion.getRegionZ())
                .setInitialMax(1024)
                .setStyle(ProgressBarStyle.ASCII)
                .setConsumer(new ConsoleMultiProgressBarConsumer(System.out, FIRST.getAndSet(false)))
                .build()) {
            /*for (int i = 0; i < 32; i++) {
                for (int j = 0; j < 32; j++) {
                    pb.step();
                    int cx = i + (srcRegion.getRegionX() * 32), cz = j + (srcRegion.getRegionZ() * 32);
                    ChunkColumn chunk;
                    try {
                        chunk = srcRegion.getChunk(cx, cz);
                    } catch (AnvilException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (chunk != null && chunk.getGenerationStatus() == ChunkColumn.GenerationStatus.Full) {
                        NbtMapBuilder builder = NbtMap.builder();
                        try {
                            builder.putString("Status", chunk.getGenerationStatus().name())
                                    .putInt("xPos", chunk.getX())
                                    .putInt("zPos", chunk.getZ())
                                    .put("Heightmaps", NbtMap.builder().putLongArray("WORLD_SURFACE", chunk.getWorldSurfaceHeightMap().compact().copyArray()).build());
                            List<NbtMap> sections = new ArrayList<>();
                            for (int sectionY = (srcRegion.getMinY() << 4); sectionY < (srcRegion.getMaxY() << 4); sectionY++) {
                                NbtMapBuilder sectionNBT = NbtMap.builder();
                                ChunkSection section = chunk.getSection((byte) sectionY);
                                if (section.getEmpty()) break;
                                Palette<Integer> blockStatesPalette = new Palette<>(AIR_BLOCK_STATE_HASH);
                                Palette<Identifier> biomePalette = new Palette<>(VanillaBiomeId.PLAINS.getIdentifier());
                                for (int x = 0; x < 16; ++x) {
                                    for (int y = 0; y < 16; ++y) {
                                        for (int z = 0; z < 16; ++z) {
                                            BlockState blockState;
                                            try {
                                                blockState = section.get(x, y, z);
                                            } catch (AnvilException e) {
                                                throw new RuntimeException(e);
                                            }
                                            Integer beBlockStateHash = Mapping.getBeBlockStateHash(MappingUtils.convertBlockState(blockState));
                                            Integer beBiomeId = Mapping.getBeBiomeId(section.getBiome(x, y, z));
                                            Identifier biome = beBiomeId == null ? VanillaBiomeId.values()[1].getIdentifier() : VanillaBiomeIdUtils.fromId(beBiomeId).getIdentifier();
                                            int blockStateHash = beBlockStateHash == null ? AIR_BLOCK_STATE_HASH : beBlockStateHash;
                                            int index = Chunk.index(x, y, z);
                                            biomePalette.set(index, biome);
                                            blockStatesPalette.set(index, blockStateHash);
                                        }
                                    }
                                }
                                sectionNBT.putCompound("block_states", blockStatesPalette.toNBT(Function.identity()))
                                        .putCompound("biomes", biomePalette.toNBT(Identifier::toString)).build();
                                byte[] blockLights = section.getBlockLights();
                                if (blockLights.length == 2048) {
                                    sectionNBT.putByteArray("BlockLight", blockLights);
                                }
                                byte[] skyLights = section.getSkyLights();
                                if (skyLights.length == 2048) {
                                    sectionNBT.putByteArray("SkyLight", skyLights);
                                }
                                sections.add(sectionNBT.build());
                            }
                            builder.putList("sections", NbtType.COMPOUND, sections);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        NbtMap build = builder.build();
                        try {
                            targetRegion.writeChunk(i, j, build);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (chunk != null) {
                        srcRegion.forget(chunk);
                    }
                }
            }*/
        }
    }
}
