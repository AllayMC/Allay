package org.allaymc.server.world.storage;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AllayMemoryWorldStorage implements WorldStorage {
    private final String worldName;
    Map<Long,Chunk>[] dims = new Long2ObjectOpenHashMap[]{
            new Long2ObjectOpenHashMap<Chunk>(),
            new Long2ObjectOpenHashMap<Chunk>(),
            new Long2ObjectOpenHashMap<Chunk>()};
    WorldData worldData;

    public AllayMemoryWorldStorage(Path path) {
        this.worldName = path.getName(path.getNameCount() - 1).toString();
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return CompletableFuture
                .supplyAsync(() -> readChunkSync(chunkX, chunkZ, dimensionInfo), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public Chunk readChunkSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        if (!containChunk(chunkX, chunkZ, dimensionInfo)) {
            return AllayUnsafeChunk.builder().newChunk(chunkX, chunkZ, dimensionInfo).toSafeChunk();
        }
        return dims[dimensionInfo.dimensionId()].get(getKey(chunkX,chunkZ));
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        return CompletableFuture
                .runAsync(() -> writeChunkSync(chunk), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public void writeChunkSync(Chunk chunk) {
        dims[chunk.getDimensionInfo().dimensionId()].put(getKey(chunk.getX(),chunk.getZ()), chunk);
    }

    @Override
    public boolean containChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return dims[dimensionInfo.dimensionId()].containsKey(getKey(chunkX, chunkZ));
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        this.worldData = worldData;
    }

    @Override
    public WorldData readWorldData() {
        if (worldData == null) {
            worldData = AllayWorldData
                    .builder()
                    .displayName(worldName)
                    .build();
        }
        return worldData;
    }

    static private long getKey(int chunkX, int chunkZ) {
        return (long) chunkX << 32 | (long) chunkZ;
    }
}