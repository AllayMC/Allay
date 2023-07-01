package cn.allay.server.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayChunkService implements ChunkService {

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectOpenHashMap<>();
    private final Map<Long, CompletableFuture<Chunk>> loadingChunks = new ConcurrentHashMap<>();
    @Getter
    private final World world;
    @Getter
    private final WorldGenerationService worldGenerationService;
    @Getter
    private final WorldStorage worldStorage;
    private record ChunkAddingQueueEntry(Chunk chunk, long hashXZ) {}
    private final Queue<ChunkAddingQueueEntry> chunkAddingQueue = new ConcurrentLinkedQueue<>();

    public AllayChunkService(World world, Function<ChunkService, WorldGenerationService> worldGenerationServiceSupplier, WorldStorage worldStorage) {
        this.world = world;
        this.worldGenerationService = worldGenerationServiceSupplier.apply(this);
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        addChunks();
    }

    private void addChunks() {
        while (!chunkAddingQueue.isEmpty()) {
            var entry = chunkAddingQueue.poll();
            loadedChunks.put(entry.hashXZ(), entry.chunk());
            loadingChunks.remove(entry.hashXZ());
        }
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        chunkAddingQueue.offer(new ChunkAddingQueueEntry(chunk, HashUtils.hashXZ(x, z)));
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return loadedChunks.get(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return loadedChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public boolean isChunkLoading(int x, int z) {
        return loadingChunks.containsKey(HashUtils.hashXZ(x, z));
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunk = getChunk(x, z);
        if (chunk != null) {
            return CompletableFuture.completedFuture(chunk);
        }
        return loadChunk(x, z);
    }

    private CompletableFuture<Chunk> loadChunk(int x, int z) {
        var hashXZ = HashUtils.hashXZ(x, z);
        var loadingChunk = loadingChunks.get(hashXZ);
        if (loadingChunk != null) {
            return loadingChunk;
        }
        var future = worldStorage
                .readChunk(x, z, world.getDimensionInfo())
                .thenApply(loadedChunk -> {
                    if (loadedChunk != null) {
                        setChunk(x, z, loadedChunk);
                        return loadedChunk;
                    }
                    //TODO generate chunk
                    return null;
                });
        loadingChunks.put(hashXZ, future);
        return future;
    }

    @Override
    public int maxX() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int maxZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int minX() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int minZ() {
        return Integer.MIN_VALUE;
    }
}
