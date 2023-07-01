package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@NotThreadSafe
public class AllayNonPersistentWorldStorage implements WorldStorage {

    private final Map<Long, Chunk> chunks = new Long2ObjectOpenHashMap<>();
    private WorldData worldData = WorldData.DEFAULT;

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData) {
        return CompletableFuture.completedFuture(chunks.get(HashUtils.hashXZ(x, z)));
    }

    @Override
    public CompletableFuture<Void> writeChunk(int x, int z, Chunk chunk) {
        chunks.put(HashUtils.hashXZ(x, z), chunk);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Boolean> containChunk(int x, int z) {
        return CompletableFuture.completedFuture(chunks.containsKey(HashUtils.hashXZ(x, z)));
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        this.worldData = worldData;
    }

    @Override
    public WorldData readWorldData() {
        return this.worldData;
    }

    @Override
    public void close() {
        //Nothing here
    }
}
