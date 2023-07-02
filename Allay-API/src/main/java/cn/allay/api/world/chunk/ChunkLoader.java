package cn.allay.api.world.chunk;

import cn.allay.api.math.location.FixedLoc;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {
    FixedLoc<Float> getLocation();

    boolean isLoaderActive();

    void onChunkLoad(Chunk chunk, int hashXZ);

    void onChunkUnload(Chunk chunk, int hashXZ);
}