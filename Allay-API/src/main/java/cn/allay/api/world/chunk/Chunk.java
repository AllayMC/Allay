package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.world.DimensionInfo;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.Range;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.Consumer;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {
    int SUB_CHUNK_VERSION = 9;
    int SECTION_SIZE = 16 * 16 * 16;

    static @Range(from = 0, to = 4095) int index(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        //The bedrock chunk order is xzy,the chunk order of java version is yzx
        return (x << 8) + (z << 4) + y;
    }

    DimensionInfo getDimensionInfo();

    void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState expectedValue, BlockState newValue);

    void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue);

    void batchProcess(@Nullable Consumer<BlockOperate> blockOperate, @Nullable Consumer<HeightOperate> heightOperate, @Nullable Consumer<SkyLightOperate> skyLightOperate, @Nullable Consumer<BlockLightOperate> blockLightOperate);

    LevelChunkPacket createLevelChunkPacket();
}
