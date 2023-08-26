package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.world.biome.BiomeType;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {
    void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState expectedValue, BlockState newValue, int layer);

    void compareAndSetBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType expectedValue, BiomeType newValue);

    void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int expectedValue, @Range(from = -512, to = 511) int newValue);

    void batchProcess(UnsafeChunkOperate operate);

    @ApiStatus.Internal
    LevelChunkPacket createLevelChunkPacket();
}
