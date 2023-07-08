package cn.allay.api.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
public interface UnsafeChunk extends BlockOperate, HeightOperate, SkyLightOperate, BlockLightOperate {
    DimensionInfo getDimensionInfo();

    int getChunkX();

    int getChunkZ();

    default long computeChunkHash() {
        return HashUtils.hashXZ(getChunkX(), getChunkZ());
    }

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);

    int getChunkLoaderCount();
}
