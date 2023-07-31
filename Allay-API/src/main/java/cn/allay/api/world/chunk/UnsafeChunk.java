package cn.allay.api.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public interface UnsafeChunk extends SectionOperate, HeightOperate, LightOperate {
    DimensionInfo getDimensionInfo();

    int getChunkX();

    void setChunkX(int chunkX);

    int getChunkZ();

    void setChunkZ(int chunkZ);

    @ApiStatus.Internal
    @Nullable
    ChunkSection getSection(int y);

    @ApiStatus.Internal
    @NotNull
    ChunkSection getOrCreateSection(int y);

    default long computeChunkHash() {
        return HashUtils.hashXZ(getChunkX(), getChunkZ());
    }
}
