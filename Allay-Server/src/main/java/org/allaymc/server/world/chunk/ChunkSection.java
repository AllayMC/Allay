package org.allaymc.server.world.chunk;

import io.netty.buffer.ByteBuf;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.datastruct.NibbleArray;
import org.allaymc.server.world.palette.Palette;

import javax.annotation.concurrent.NotThreadSafe;

import static org.allaymc.api.block.type.BlockTypes.AIR;
import static org.allaymc.api.world.chunk.UnsafeChunk.index;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public record ChunkSection(
        byte sectionY,
        Palette<BlockState>[] blockLayer,
        Palette<BiomeType> biomes,
        NibbleArray blockLights,
        NibbleArray skyLights
) {
    public static final int LAYER_COUNT = 2;
    public static final int VERSION = 9;

    @SuppressWarnings("unchecked")
    public ChunkSection(byte sectionY) {
        this(
                sectionY,
                new Palette[]{new Palette<>(AIR.getDefaultState()), new Palette<>(AIR.getDefaultState())},
                new Palette<>(VanillaBiomeId.PLAINS),
                new NibbleArray(Chunk.SECTION_SIZE),
                new NibbleArray(Chunk.SECTION_SIZE)
        );
    }

    public ChunkSection(byte sectionY, Palette<BlockState>[] blockLayer) {
        this(
                sectionY, blockLayer,
                new Palette<>(VanillaBiomeId.PLAINS),
                new NibbleArray(Chunk.SECTION_SIZE),
                new NibbleArray(Chunk.SECTION_SIZE)
        );
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        return blockLayer[layer].get(index(x, y, z));
    }

    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        blockLayer[layer].set(index(x, y, z), blockState);
    }

    public void setBiomeType(int x, int y, int z, BiomeType biomeType) {
        biomes.set(index(x, y, z), biomeType);
    }

    public BiomeType getBiomeType(int x, int y, int z) {
        return biomes.get(index(x, y, z));
    }

    public byte getBlockLight(int x, int y, int z) {
        return blockLights.get(index(x, y, z));
    }

    public byte getSkyLight(int x, int y, int z) {
        return skyLights.get(index(x, y, z));
    }

    public void setBlockLight(int x, int y, int z, byte light) {
        blockLights.set(index(x, y, z), light);
    }

    public void setSkyLight(int x, int y, int z, byte light) {
        skyLights.set(index(x, y, z), light);
    }

    public boolean isEmpty() {
        return blockLayer[0].isEmpty() && blockLayer[0].get(0) == AIR.getDefaultState();
    }

    public void writeToNetwork(ByteBuf byteBuf) {
        byteBuf.writeByte(VERSION);
        // block layer count
        byteBuf.writeByte(LAYER_COUNT);
        byteBuf.writeByte(sectionY & 0xFF);

        for (var palette : blockLayer) palette.writeToNetwork(byteBuf, BlockState::blockStateHash);
    }
}
