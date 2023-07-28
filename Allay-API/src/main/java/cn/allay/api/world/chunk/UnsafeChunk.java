package cn.allay.api.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;

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

    default long computeChunkHash() {
        return HashUtils.hashXZ(getChunkX(), getChunkZ());
    }
}
