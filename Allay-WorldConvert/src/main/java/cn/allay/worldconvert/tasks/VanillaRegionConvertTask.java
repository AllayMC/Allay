package cn.allay.worldconvert.tasks;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.mapping.Mapping;
import cn.allay.api.utils.VanillaBiomeIdUtils;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.palette.Palette;
import cn.allay.api.world.storage.AnvilRegionFile;
import cn.allay.worldconvert.utils.MappingUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.jglrxavpok.hephaistos.mca.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.function.Function;

/**
 * Allay Project 8/8/2023
 *
 * @author Cool_Loong
 */
public class VanillaRegionConvertTask extends RecursiveAction {
    private final static Integer AIR_BLOCK_STATE_HASH = -604749536;
    final RegionFile srcRegion;
    final AnvilRegionFile targetRegion;

    public VanillaRegionConvertTask(RegionFile srcRegion, AnvilRegionFile targetRegion) {
        this.srcRegion = srcRegion;
        this.targetRegion = targetRegion;
    }

    @Override
    protected void compute() {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
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
            }
        }
    }
}
