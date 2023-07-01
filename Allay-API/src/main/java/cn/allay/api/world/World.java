package cn.allay.api.world;

import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.world.storage.WorldStorage;

/**
 * Describe a world
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface World {
    WorldStorage getWorldStorage();

    String getName();

    DimensionInfo getDimensionInfo();

    FixedLoc<Float> getSpawnLocation();

    void setSpawnLocation(Loc<Float> newSpawn);

    Difficulty getDifficulty();
}
