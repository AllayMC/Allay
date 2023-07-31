package cn.allay.api.world.chunk;

import cn.allay.api.math.Location3dc;

import java.util.Set;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkLoader {

    Location3dc getLocation();

    boolean isLoaderActive();

    int getChunkLoadingRadius();

    void setChunkLoadingRadius(int radius);

    void preSendChunks(Set<Long> chunkHashes);

    void sendChunk(Chunk chunk);

    void unloadChunks(Set<Long> chunkHashes);

    default void unloadChunk(Long chunkHash) {
        unloadChunks(Set.of(chunkHash));
    }
}