package cn.allay.api.world;

import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.server.Server;
import cn.allay.api.world.chunk.ChunkAccessible;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.storage.WorldStorage;

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

    WorldStorage getWorldStorage();

    String getName();

    DimensionInfo getDimensionInfo();

    WorldType getWorldType();

    int getTickingRadius();

    FixedLoc<Float> getSpawnLocation();

    void setSpawnLocation(Loc<Float> newSpawn);

    Difficulty getDifficulty();

    WorldGenerator getWorldGenerator();

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
