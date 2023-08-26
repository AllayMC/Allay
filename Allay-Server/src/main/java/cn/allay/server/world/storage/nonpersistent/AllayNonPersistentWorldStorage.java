package cn.allay.server.world.storage.nonpersistent;

import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
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
    public CompletableFuture<Chunk> readChunk(int x, int z, World world) {
        var chunk = chunks.get(HashUtils.hashXZ(x, z));
        if (chunk != null) readEntities(x, z).stream().map(nbt -> Entity.fromNBT(world, nbt)).forEach(chunk::addEntity);
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public CompletableFuture<Void> writeChunk(int x, int z, Chunk chunk) {
        chunks.put(HashUtils.hashXZ(x, z), chunk);
        writeEntities(x, z, chunk.getEntities().values());
        return CompletableFuture.completedFuture(null);
    }

    protected Set<NbtMap> readEntities(int chunkX, int chunkZ) throws WorldStorageException {
        return entities.getOrDefault(HashUtils.hashXZ(chunkX, chunkZ), Set.of());
    }

    protected void writeEntities(int chunkX, int chunkZ, Collection<Entity> entities) throws WorldStorageException {
        this.entities.put(HashUtils.hashXZ(chunkX, chunkZ), entities.stream().map(Entity::save).collect(Collectors.toSet()));
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
    public void close() {
        //Nothing here
    }
}
