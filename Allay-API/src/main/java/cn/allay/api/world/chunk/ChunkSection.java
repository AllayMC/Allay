package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.datastruct.NibbleArray;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.palette.Palette;
import io.netty.buffer.ByteBuf;

import javax.annotation.concurrent.NotThreadSafe;

import static cn.allay.api.world.chunk.BaseChunk.index;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public record ChunkSection(int sectionY,
                           Palette<BlockState>[] blockLayer,
                           Palette<BiomeType> biomes,
                           NibbleArray blockLights,
                           NibbleArray skyLights) {
    public static final int LAYER_COUNT = 2;
    public static final int VERSION = 9;

    public ChunkSection(int sectionY) {
        this(sectionY,
                new Palette[]{new Palette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState()), new Palette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState())},
                new Palette<>(VanillaBiomeId.PLAINS),
                new NibbleArray(Chunk.SECTION_SIZE),
                new NibbleArray(Chunk.SECTION_SIZE));
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        return blockLayer[layer].get(index(x, y, z));
    }

    public void setBlockState(int x, int y, int z, int layer, BlockState blockState) {
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
        return blockLayer[0].isEmpty() && blockLayer[0].get(0) == VanillaBlockTypes.AIR_TYPE.getDefaultState();
    }

    public void writeToNetwork(ByteBuf byteBuf) {
        byteBuf.writeByte(VERSION);
        //block layer count
        byteBuf.writeByte(LAYER_COUNT);
        byteBuf.writeByte(sectionY);

        blockLayer[0].writeToNetwork(byteBuf, BlockState::blockStateHash);
        blockLayer[1].writeToNetwork(byteBuf, BlockState::blockStateHash);
    }
}
