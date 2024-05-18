package org.allaymc.server.world.storage;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityHelper;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityHelper;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.exception.WorldStorageException;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.cloudburstmc.nbt.NbtMap;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 *
 * TODO: optimize it for better memory footprint
 */
@NotThreadSafe
public class AllayNonPersistentWorldStorage implements WorldStorage {
    private final Map<Long, Chunk> chunks = new Long2ObjectOpenHashMap<>();
    private final Map<Long, Set<NbtMap>> entities = new Long2ObjectOpenHashMap<>();
    private final Map<Long, Set<NbtMap>> blockEntities = new Long2ObjectOpenHashMap<>();
    private WorldData worldData;

    public AllayNonPersistentWorldStorage() {
        this.worldData = WorldData.builder().build();
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionInfo) {
        return CompletableFuture.completedFuture(readChunkSynchronously(x, z, dimensionInfo));
    }

    @Override
    public Chunk readChunkSynchronously(int x, int z, DimensionInfo dimensionInfo) throws WorldStorageException {
        Dimension dimension = Server.getInstance().getWorldPool().getWorld(worldData.getName()).getDimension(dimensionInfo.dimensionId());
        long l = HashUtils.hashXZ(x, z);
        var chunk = chunks.get(l);
        if (chunk == null) {
            chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimensionInfo).toSafeChunk();
        }
        readEntities(l).stream().map(nbt -> EntityHelper.fromNBT(dimension, nbt)).forEach(e -> dimension.getEntityService().addEntity(e));
        readBlockEntities(l).stream().map(nbt -> BlockEntityHelper.fromNBT(dimension, nbt)).forEach(chunk::addBlockEntity);
        return chunk;
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        long l = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
        chunks.put(l, chunk);
        writeEntities(l, chunk.getEntities().values());
        writeBlockEntities(l, chunk.getBlockEntities().values());
        return CompletableFuture.completedFuture(null);
    }

    protected Set<NbtMap> readEntities(long chunkHash) throws WorldStorageException {
        return entities.getOrDefault(chunkHash, Set.of());
    }

    protected Set<NbtMap> readBlockEntities(long chunkHash) throws WorldStorageException {
        return blockEntities.getOrDefault(chunkHash, Set.of());
    }

    protected void writeEntities(long chunkHash, Collection<Entity> entities) throws WorldStorageException {
        this.entities.put(chunkHash, entities.stream().map(Entity::saveNBT).collect(Collectors.toSet()));
    }

    protected void writeBlockEntities(long chunkHash, Collection<BlockEntity> blockEntities) throws WorldStorageException {
        this.blockEntities.put(chunkHash, blockEntities.stream().map(BlockEntity::saveNBT).collect(Collectors.toSet()));
    }

    @Override
    public boolean containChunk(int x, int z, DimensionInfo dimensionInfo) {
        return chunks.containsKey(HashUtils.hashXZ(x, z));
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
    public WorldData getWorldDataCache() {
        return worldData;
    }

    @Override
    public void close() {
        //Nothing here
    }
}
