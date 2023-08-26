package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.entity.Entity;
import cn.allay.api.server.Server;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.storage.WorldStorage;
import cn.allay.api.world.storage.WorldStorageException;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
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
 */
@NotThreadSafe
public class AllayNonPersistentWorldStorage implements WorldStorage {

    private final Map<Long, Chunk> chunks = new Long2ObjectOpenHashMap<>();
    private final Map<Long, Set<NbtMap>> entities = new Long2ObjectOpenHashMap<>();
    private WorldData worldData = WorldData.DEFAULT;

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z) {
        World world = Server.getInstance().getWorldPool().getWorld(worldData.getLevelName());
        long l = HashUtils.hashXZ(x, z);
        var chunk = chunks.get(l);
        if (chunk != null) readEntities(l).stream().map(nbt -> Entity.fromNBT(world, nbt)).forEach(chunk::addEntity);
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        long l = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
        chunks.put(l, chunk);
        writeEntities(l, chunk.getEntities().values());
        return CompletableFuture.completedFuture(null);
    }

    protected Set<NbtMap> readEntities(long chunkHash) throws WorldStorageException {
        return entities.getOrDefault(chunkHash, Set.of());
    }

    protected void writeEntities(long chunkHash, Collection<Entity> entities) throws WorldStorageException {
        this.entities.put(chunkHash, entities.stream().map(Entity::save).collect(Collectors.toSet()));
    }

    @Override
    public boolean containChunk(int x, int z) {
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
