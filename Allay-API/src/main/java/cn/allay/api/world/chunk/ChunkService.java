package cn.allay.api.world.chunk;

import cn.allay.api.annotation.SlowOperation;
import cn.allay.api.world.World;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.List;
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

    @SlowOperation
    CompletableFuture<Chunk> loadChunk(int x, int z);

    void unloadChunk(int x, int z);

    void unloadChunk(long chunkHash);

    boolean isChunkLoaded(int x, int z);

    boolean isChunkLoaded(long hashXZ);

    boolean isChunkLoading(int x, int z);

    boolean isChunkLoading(long hashXZ);

    boolean isChunkUnloaded(int x, int z);

    boolean isChunkUnloaded(long hashXZ);

    World getWorld();

    WorldGenerationService getWorldGenerationService();

    WorldStorage getWorldStorage();

    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    void addChunkLoader(ChunkLoader chunkLoader);

    void removeChunkLoader(ChunkLoader chunkLoader);
}
