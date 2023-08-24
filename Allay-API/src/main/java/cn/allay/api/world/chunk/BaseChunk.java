package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.entity.Entity;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
public interface BaseChunk {
    int SECTION_SIZE = 16 * 16 * 16;
    int STATE_NEW = 0;
    int STATE_GENERATED = 1;
    int STATE_POPULATED = 2;
    int STATE_FINISHED = 3;

    int getState();

    void setState(int next);

    DimensionInfo getDimensionInfo();

    int getX();

    int getZ();

    void addEntity(@NotNull Entity entity);

    void removeEntity(@NotNull Entity entity);

    @UnmodifiableView
    Set<Entity> getEntities();

    @ApiStatus.Internal
    @Nullable
    ChunkSection getSection(@Range(from = 0, to = 63) int y);

    @ApiStatus.Internal
    @NotNull
    ChunkSection getOrCreateSection(@Range(from = 0, to = 63) int y);

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();

    void addChunkPacket(BedrockPacket packet);

    void sendChunkPacket(BedrockPacket packet);

    void sendChunkPackets();

    void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    default void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int layer);

    default BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int height);

    int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    short[] getHeights();

    void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light);

    @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light);

    @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    static @Range(from = 0, to = 4095) int index(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        //The bedrock chunk order is xzy,the chunk order of java version is yzx
        return (x << 8) + (z << 4) + y;
    }
}
