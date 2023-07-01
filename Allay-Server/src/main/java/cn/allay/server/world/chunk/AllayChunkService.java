package cn.allay.server.world.chunk;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.generator.WorldGenerationService;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayChunkService implements ChunkService {

    private final Map<Long, Chunk> loadedChunks = new Long2ObjectOpenHashMap<>();

    private final WorldGenerationService worldGenerationService;
    private final WorldStorage worldStorage;
    private record ChunkAddingQueueEntry(Chunk chunk, long hashXZ) {}
    private final Queue<ChunkAddingQueueEntry> chunkAddingQueue = new ConcurrentLinkedQueue<>();

    public AllayChunkService(Function<ChunkService, WorldGenerationService> worldGenerationServiceSupplier, WorldStorage worldStorage) {
        this.worldGenerationService = worldGenerationServiceSupplier.apply(this);
        this.worldStorage = worldStorage;
    }

    @Override
    public void tick() {
        while (!chunkAddingQueue.isEmpty()) {
            var entry = chunkAddingQueue.poll();
            loadedChunks.put(entry.hashXZ(), entry.chunk());
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
