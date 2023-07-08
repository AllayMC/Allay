package cn.allay.api.world.chunk;

import cn.allay.api.math.location.FixedLoc;

import java.util.Set;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {
    FixedLoc<Float> getLocation();

    boolean isLoaderActive();

    int getChunkLoadingRadius();

    void sendChunk(Chunk chunk);

    void unloadChunks(Set<Long> chunkHashes);

    default void unloadChunk(Long chunkHash) {
        unloadChunks(Set.of(chunkHash));
    }
}