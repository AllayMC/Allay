package org.allaymc.server.world.chunk;

import io.netty.buffer.ByteBuf;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.world.palette.Palette;

import javax.annotation.concurrent.NotThreadSafe;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.world.chunk.UnsafeChunk.index;

/**
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public record ChunkSection(
        byte sectionY,
        Palette<BlockState>[] blockLayers,
        Palette<BiomeType> biomes
) {
    public static final int LAYER_COUNT = 2;
    public static final int VERSION = 9;

    @SuppressWarnings("unchecked")
    public ChunkSection(byte sectionY) {
        this(sectionY, new Palette[]{new Palette<>(AIR.getDefaultState()), new Palette<>(AIR.getDefaultState())}, new Palette<>(BiomeId.PLAINS));
    }

    public ChunkSection(byte sectionY, Palette<BlockState>[] blockLayer) {
        this(sectionY, blockLayer, new Palette<>(BiomeId.PLAINS));
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        return blockLayers[layer].get(index(x, y, z));
    }

    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        blockLayers[layer].set(index(x, y, z), blockState);
    }

    public void setBiomeType(int x, int y, int z, BiomeType biomeType) {
        biomes.set(index(x, y, z), biomeType);
    }

    public BiomeType getBiomeType(int x, int y, int z) {
        return biomes.get(index(x, y, z));
    }

    public boolean isAirSection() {
        return blockLayers[0].isEmpty() && blockLayers[0].get(0) == AIR.getDefaultState();
    }

    public void writeToNetwork(ByteBuf byteBuf) {
        byteBuf.writeByte(VERSION);
        // Block layer count
        byteBuf.writeByte(LAYER_COUNT);
        // Extra byte since version 9
        byteBuf.writeByte(sectionY & 0xFF);

        for (var blockLayer : blockLayers) blockLayer.writeToNetwork(byteBuf, BlockState::blockStateHash);
    }
}
