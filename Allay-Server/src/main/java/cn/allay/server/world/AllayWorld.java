package cn.allay.server.world;

import cn.allay.api.client.Client;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.math.position.Position3ic;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.Difficulty;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.service.BlockUpdateService;
import cn.allay.api.world.service.ChunkService;
import cn.allay.api.world.service.EntityPhysicsService;
import cn.allay.api.world.storage.WorldStorage;
import cn.allay.server.GameLoop;
import cn.allay.server.scheduler.AllayScheduler;
import cn.allay.server.world.service.AllayBlockUpdateService;
import cn.allay.server.world.service.AllayChunkService;
import cn.allay.server.world.service.AllayEntityPhysicsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final WorldData worldData;
    @Getter
    protected final WorldGenerator worldGenerator;
    @Getter
    protected final Server server;
    @Getter
    protected final ChunkService chunkService;
    @Getter
    protected final EntityPhysicsService entityPhysicsService;
    @Getter
    protected final BlockUpdateService blockUpdateService;
    @Getter
    protected final Scheduler worldScheduler;
    @Getter
    protected final Thread worldMainThread;
    protected final Set<Client> clients;
    protected final GameLoop gameLoop;

    private AllayWorld(Server server,
                       WorldStorage worldStorage,
                       WorldData worldData,
                       WorldGenerator worldGenerator) {
        gameLoop = GameLoop.builder()
                .onTick(gameLoop -> {
                    try {
                        tick();
                    } catch (Throwable throwable) {
                        log.error("Error while ticking level " + getName(), throwable);
                    }
                })
                .build();
        this.worldStorage = worldStorage;
        this.worldData = worldData == null ? worldStorage.getWorldDataCache() : worldData;
        this.worldGenerator = worldGenerator;
        this.server = server;
        this.chunkService = new AllayChunkService(this, worldStorage);
        this.entityPhysicsService = new AllayEntityPhysicsService(this);
        this.blockUpdateService = new AllayBlockUpdateService(this);
        this.worldScheduler = new AllayScheduler();
        this.clients = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.worldMainThread = Thread.ofPlatform()
                .name("World Thread - " + this.worldData.getLevelName())
                .unstarted(gameLoop::startLoop);
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    @Override
    public float getTps() {
        return gameLoop.getTps();
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
        long currentTick = getTick();
        chunkService.tick();
        entityPhysicsService.tick();
        worldScheduler.tick();
        blockUpdateService.tick(currentTick);
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
        entity.spawnTo(chunk.getClientChunkLoaders());
        entityPhysicsService.addEntity(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        var chunk = entity.getCurrentChunk();
        if (chunk == null)
            throw new IllegalStateException("Trying to despawn an entity from an unload chunk!");
        entityPhysicsService.removeEntity(entity);
        chunk.removeEntity(entity.getUniqueId());
        entity.despawnFromAll();
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
        chunkService.addChunkLoader(client);
        addEntity(client.getPlayerEntity());
    }

    @Override
    public void removeClient(Client client) {
        removeEntity(client.getPlayerEntity());
        chunkService.removeChunkLoader(client);
        clients.remove(client);
    }

    @Override
    public Collection<Client> getClients() {
        return Collections.unmodifiableCollection(clients);
    }

    @Override
    public void close() {
        getChunkService().unloadAllChunks();
        getWorldStorage().close();
    }


    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public static class WorldBuilder {
        @Nullable
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
