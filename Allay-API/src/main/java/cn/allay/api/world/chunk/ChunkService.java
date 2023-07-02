package cn.allay.api.world.chunk;

import cn.allay.api.world.World;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkService extends ChunkAccessible {
    void tick();

    CompletableFuture<Chunk> getOrLoadChunk(int x, int z);

    boolean isChunkLoaded(int x, int z);

    boolean isChunkLoading(int x, int z);

    World getWorld();

    WorldGenerationService getWorldGenerationService();

    WorldStorage getWorldStorage();

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);
}
