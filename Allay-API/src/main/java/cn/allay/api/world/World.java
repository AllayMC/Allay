package cn.allay.api.world;

import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.network.Client;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.chunk.ChunkAccessible;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.entity.EntityService;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.storage.WorldStorage;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.Nullable;

/**
 * Describe a world
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface World extends ChunkAccessible {

    void tick();

    Server getServer();

    Scheduler getWorldScheduler();

    WorldStorage getWorldStorage();

    GameType getWorldGameType();

    void setWorldGameType(GameType gameType);

    String getName();

    DimensionInfo getDimensionInfo();

    WorldType getWorldType();

    int getTickingRadius();

    int getViewDistance();

    FixedLoc<Float> getSpawnLocation();

    void setSpawnLocation(Loc<Float> newSpawn);

    Difficulty getDifficulty();

    WorldGenerator getWorldGenerator();

    ChunkService getChunkService();

    EntityService getEntityService();

    default void addEntity(Entity entity) {
        getEntityService().addEntity(entity);
    }

    default void removeEntity(Entity entity) {
        getEntityService().removeEntity(entity);
    }

    @Nullable
    default Entity removeEntity(long entityUniqueId) {
        return getEntityService().removeEntity(entityUniqueId);
    }

    void addClient(Client client);

    void removeClient(Client client);

    @Override
    default int maxX() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int maxZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int minX() {
        return Integer.MIN_VALUE;
    }

    @Override
    default int minZ() {
        return Integer.MIN_VALUE;
    }
}
