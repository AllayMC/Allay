package org.allaymc.server.world;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.GameLoop;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.world.service.AllayBlockUpdateService;
import org.allaymc.server.world.service.AllayChunkService;
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.SetTimePacket;
import org.joml.Vector3ic;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    protected final Set<EntityPlayer> players;
    protected long nextTimeSendTick = 0;
    protected final GameLoop gameLoop;
    protected Queue<EntityUpdateOperation> entityUpdateOperationQueue = new ConcurrentLinkedQueue<>();

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
        this.players = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.worldMainThread = Thread.ofPlatform()
                .name("World Thread - " + this.worldData.getLevelName())
                .unstarted(gameLoop::startLoop);
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    protected void handleEntityUpdateQueue() {
        while (!entityUpdateOperationQueue.isEmpty()) {
            var operation = entityUpdateOperationQueue.poll();
            var entity = operation.entity;
            switch (operation.type) {
                case ADD -> {
                    var chunk = entity.getCurrentChunk();
                    if (chunk == null)
                        throw new IllegalStateException("Entity can't spawn in unloaded chunk!");
                    chunk.addEntity(entity);
                    entity.spawnTo(chunk.getPlayerChunkLoaders());
                    entityPhysicsService.addEntity(entity);
                }
                case REMOVE -> {
                    var chunk = entity.getCurrentChunk();
                    if (chunk == null)
                        throw new IllegalStateException("Trying to despawn an entity from an unload chunk!");
                    entityPhysicsService.removeEntity(entity);
                    chunk.removeEntity(entity.getUniqueId());
                    entity.despawnFromAll();
                    entity.setWillBeRemovedNextTick(false);
                }
            }
        }
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

    @Override
    public void setWorldGameType(GameType gameType) {
        this.worldData.setGameType(gameType);
    }

    protected void tick() {
        long currentTick = getTick();
        updateTime(currentTick);
        handleEntityUpdateQueue();
        chunkService.tick();
        entityPhysicsService.tick();
        worldScheduler.tick();
        blockUpdateService.tick(currentTick);
    }

    protected void updateTime(long currentTick) {
        if (worldData.getGameRules().get(GameRule.DO_DAYLIGHT_CYCLE)) {
            worldData.setTime(worldData.getTime() + 1);
            if (currentTick >= nextTimeSendTick) {
                sendWorldTimeTo(players);
                nextTimeSendTick = currentTick + 12 * 20; //Client send the time every 12 seconds
            }
        }
    }

    @Override
    public void setWorldTime(long worldTime) {
        this.worldData.setTime(worldTime);
        sendWorldTimeTo(players);
    }

    @Override
    public void sendWorldTimeTo(Collection<EntityPlayer> players) {
        var setTimePk = new SetTimePacket();
        setTimePk.setTime((int) worldData.getTime());
        for (var player : players) {
            player.sendPacket(setTimePk);
        }
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
        entityUpdateOperationQueue.add(new EntityUpdateOperation(
                entity,
                EntityUpdateType.ADD
        ));
    }

    @Override
    public void removeEntity(Entity entity) {
        entity.setWillBeRemovedNextTick(true);
        entityUpdateOperationQueue.add(new EntityUpdateOperation(
                entity,
                EntityUpdateType.REMOVE
        ));
    }

    @Override
    public void addPlayer(EntityPlayer player) {
        players.add(player);
        chunkService.addChunkLoader(player);
        addEntity(player);
    }

    @Override
    public void removePlayer(EntityPlayer player) {
        removeEntity(player);
        chunkService.removeChunkLoader(player);
        players.remove(player);
    }

    @Override
    public Collection<EntityPlayer> getPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public void close() {
        getChunkService().unloadAllChunks();
        saveWorldData();
        getWorldStorage().close();
    }

    @Override
    public void saveWorldData() {
        getWorldStorage().writeWorldData(worldData);
    }

    protected enum EntityUpdateType {
        ADD,
        REMOVE
    }

    protected record EntityUpdateOperation(Entity entity, EntityUpdateType type) {}

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
