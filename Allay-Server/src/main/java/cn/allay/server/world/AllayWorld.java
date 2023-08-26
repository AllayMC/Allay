package cn.allay.server.world;

import cn.allay.api.client.Client;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.math.position.Position3ic;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.*;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.entity.EntityPhysicsService;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.storage.WorldStorage;
import cn.allay.server.scheduler.AllayScheduler;
import cn.allay.server.utils.GameLoop;
import cn.allay.server.world.chunk.AllayChunkService;
import cn.allay.server.world.entity.AllayEntityPhysicsService;
import cn.allay.server.world.generator.AllayWorldGenerationService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {
    protected final WorldStorage worldStorage;
    @Getter
    protected final WorldData worldData;
    @Getter
    protected final WorldGenerator worldGenerator;
    @Getter
    protected final Server server;
    @Getter
    protected final WorldType worldType;
    @Getter
    protected final ForkJoinPool threadPool = new ForkJoinPool();
    @Getter
    protected final ChunkService chunkService;
    @Getter
    protected final EntityPhysicsService entityPhysicsService;
    @Getter
    protected final Scheduler worldScheduler;
    @Getter
    protected final Thread worldMainThread;

    protected final Set<Client> clients = Collections.newSetFromMap(new ConcurrentHashMap<>());

    private AllayWorld(Server server,
                       WorldStorage worldStorage,
                       WorldData worldData,
                       WorldGenerator worldGenerator) {
        this.worldStorage = worldStorage;
        this.worldData = worldData;
        this.worldGenerator = worldGenerator;
        this.worldType = worldGenerator.getGeneratorWorldType();
        this.server = server;
        this.chunkService = new AllayChunkService(
                this,
                chunkService -> new AllayWorldGenerationService(threadPool, worldGenerator),
                worldStorage);
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.worldScheduler = new AllayScheduler(Executors.newVirtualThreadPerTaskExecutor());
        this.worldMainThread = Thread.ofPlatform()
                .name("World Thread - " + worldData.getLevelName())
                .unstarted(() -> GameLoop.builder()
                        .onTick(gameLoop -> tick())
                        .build()
                        .startLoop());
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public void startTick() {
        if (worldMainThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World is already start ticking!");
        } else worldMainThread.start();
    }

    @Override
    public GameType getWorldGameType() {
        return this.worldData.getGameType();
    }

    private void tick() {
        chunkService.tick();
        entityPhysicsService.tick();
        worldScheduler.tick();
    }

    @Override
    public void setWorldGameType(GameType gameType) {
        this.worldData.setGameType(gameType);
    }

    @Override
    public String getName() {
        return this.worldData.getLevelName();
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return this.worldData.getDimensionInfo();
    }

    @Override
    public int getTickingRadius() {
        return this.worldData.getTickingRadius();
    }

    @Override
    public int getViewDistance() {
        return this.worldData.getViewDistance();
    }

    @Override
    public Position3ic getSpawnPosition() {
        Vector3ic spawnPoint = this.worldData.getSpawnPoint();
        return new Position3i(spawnPoint, this);
    }

    @Override
    public void setSpawnPosition(Position3ic newSpawn) {
        this.worldData.setSpawnPoint(newSpawn);
    }

    @Override
    public Difficulty getDifficulty() {
        return this.worldData.getDifficulty();
    }

    @Override
    public void addEntity(Entity entity) {
        var chunk = entity.getCurrentChunk();
        if (chunk == null)
            throw new IllegalStateException("Entity can't spawn in unloaded chunk!");
        chunk.addEntity(entity);
        entityPhysicsService.addEntity(entity);
        clients.forEach(entity::spawnTo);
    }

    @Override
    public void removeEntity(Entity entity) {
        var chunk = entity.getCurrentChunk();
        if (chunk == null) return;
        entityPhysicsService.removeEntity(entity);
        chunk.removeEntity(entity);
        entity.despawnFromAll();
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
        addEntity(client.getPlayerEntity());
        chunkService.addChunkLoader(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
        removeEntity(client.getPlayerEntity());
        chunkService.removeChunkLoader(client);
    }

    @Override
    public Collection<Client> getClients() {
        return Collections.unmodifiableCollection(clients);
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public static class WorldBuilder {
        private WorldData worldData;
        private WorldStorage worldStorage;
        private WorldGenerator worldGenerator;

        private WorldBuilder() {
        }

        public WorldBuilder worldData(WorldData worldData) {
            this.worldData = worldData;
            return this;
        }

        public WorldBuilder setWorldStorage(WorldStorage worldStorage) {
            this.worldStorage = worldStorage;
            return this;
        }

        public WorldBuilder setWorldGenerator(WorldGenerator worldGenerator) {
            this.worldGenerator = worldGenerator;
            return this;
        }

        public World build() {
            return new AllayWorld(Server.getInstance(), worldStorage, worldData, worldGenerator);
        }
    }
}
