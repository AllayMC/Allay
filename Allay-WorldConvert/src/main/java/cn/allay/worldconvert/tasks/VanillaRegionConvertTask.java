package cn.allay.worldconvert.tasks;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.mapping.Mapping;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.palette.Palette;
import cn.allay.worldconvert.utils.MappingUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.jglrxavpok.hephaistos.mca.*;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

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
    final RegionFile regionFile;
    int index;
    int x;
    int z;

    public VanillaRegionConvertTask(RegionFile regionFile, int index) {
        this.regionFile = regionFile;
        this.index = index;
        this.x = index >>> 5;
        this.z = index & 0x0000001F;
    }

    @Override
    protected void compute() {
        if (index > 0) {
            if (index == 1) {
                invokeAll(new VanillaRegionConvertTask(regionFile, --index));
            } else {
                invokeAll(new VanillaRegionConvertTask(regionFile, --index), new VanillaRegionConvertTask(regionFile, --index));
            }
        }
        try {
            ChunkColumn chunk = regionFile.getChunk(x, z);
            if (chunk != null && chunk.getGenerationStatus() == ChunkColumn.GenerationStatus.Full) {
                NBTCompound compound = NBT.Compound(builder -> {
                    builder.setString("Status", chunk.getGenerationStatus().name())
                            .setInt("xPos", chunk.getX())
                            .setInt("zPos", chunk.getZ())
                            .set("Heightmaps", NBT.Compound(builder2 -> builder2.setLongArray("WORLD_SURFACE", chunk.getWorldSurfaceHeightMap().compact().copyArray())));
                    List<NBTCompound> sections = new ArrayList<>();
                    for (int i = (regionFile.getMinY() << 4); i < (regionFile.getMaxY() << 4); i++) {
                        NbtMapBuilder sectionNBT = NbtMap.builder();
                        ChunkSection section = chunk.getSection((byte) i);
                        Palette<Integer> blockStatesPalette = new Palette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState().blockStateHash());
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
                                    Identifier biome = beBiomeId == null ? VanillaBiomeId.values()[1].getIdentifier() : VanillaBiomeId.values()[beBiomeId].getIdentifier();
                                    int blockStateHash = beBlockStateHash == null ? VanillaBlockTypes.AIR_TYPE.getDefaultState().blockStateHash() : beBlockStateHash;
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
                        sections.add(MappingUtils.convertNBT(sectionNBT.build()));
                    }
                });
                regionFile.writeColumnData(compound, x, z);
            }
        } catch (AnvilException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
