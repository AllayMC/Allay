package org.allaymc.server.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.world.storage.leveldb.data.ChunkSectionVersion;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.utils.hash.HashUtils.hashChunkSectionXYZ;

/**
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public record AllayChunkSection(
        byte sectionY,
        Palette<BlockState>[] blockLayers,
        Palette<BiomeType> biomes
) implements ChunkSection {
    public static final int LAYER_COUNT = 2;
    public static final int CURRENT_CHUNK_SECTION_VERSION = ChunkSectionVersion.PALETTED_MULTI_WITH_OFFSET.ordinal();

    @SuppressWarnings("unchecked")
    public AllayChunkSection(byte sectionY) {
        this(sectionY, new Palette[]{new Palette<>(AIR.getDefaultState()), new Palette<>(AIR.getDefaultState())}, new Palette<>(BiomeTypes.PLAINS));
    }

    public AllayChunkSection(byte sectionY, Palette<BlockState>[] blockLayer) {
        this(sectionY, blockLayer, new Palette<>(BiomeTypes.PLAINS));
    }

    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        return blockLayers[layer].get(hashChunkSectionXYZ(x, y, z));
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        blockLayers[layer].set(hashChunkSectionXYZ(x, y, z), blockState);
    }

    @Override
    public BiomeType getBiomeType(int x, int y, int z) {
        return biomes.get(hashChunkSectionXYZ(x, y, z));
    }

    @Override
    public void setBiomeType(int x, int y, int z, BiomeType biomeType) {
        biomes.set(hashChunkSectionXYZ(x, y, z), biomeType);
    }

    @Override
    public boolean isAirSection() {
        return blockLayers[0].oneEntryOnly();
    }

    public boolean hasDirtyBlockLayer() {
        for (var layer : blockLayers) {
            if (layer.isDirty()) {
                return true;
            }
        }

        return false;
    }
}
