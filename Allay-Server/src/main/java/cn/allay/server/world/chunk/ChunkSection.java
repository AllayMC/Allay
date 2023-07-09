package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.datastruct.NibbleArray;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.palette.Palette;
import io.netty.buffer.ByteBuf;

import javax.annotation.concurrent.NotThreadSafe;

import static cn.allay.api.world.chunk.Chunk.SUB_CHUNK_VERSION;
import static cn.allay.api.world.chunk.Chunk.index;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public record ChunkSection(int sectionY,
                           Palette<BlockState> blockLayer0,
                           Palette<BlockState> blockLayer1,
                           Palette<BiomeType> biomes,
                           NibbleArray blockLights,
                           NibbleArray skyLights) {
    public ChunkSection(int sectionY) {
        this(sectionY,
                new Palette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState()),
                new Palette<>(VanillaBlockTypes.AIR_TYPE.getDefaultState()),
                new Palette<>(VanillaBiomeId.PLAINS),
                new NibbleArray(Chunk.SECTION_SIZE),
                new NibbleArray(Chunk.SECTION_SIZE));
    }

    public BlockState getBlock(int x, int y, int z, boolean layer) {
        if (layer) {
            return blockLayer1.get(index(x, y, z));
        } else {
            return blockLayer0.get(index(x, y, z));
        }
    }

    public void setBlock(int x, int y, int z, boolean layer, BlockState blockState) {
        if (layer) {
            blockLayer1.set(index(x, y, z), blockState);
        } else {
            blockLayer0.set(index(x, y, z), blockState);
        }
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

    public void writeToNetwork(ByteBuf byteBuf) {
        byteBuf.writeByte(SUB_CHUNK_VERSION);
        //block layer count
        byteBuf.writeByte(2);
        byteBuf.writeByte(sectionY);

        blockLayer0.writeToNetwork(byteBuf, BlockState::paletteIndex);
        blockLayer1.writeToNetwork(byteBuf, BlockState::paletteIndex);
    }
}
