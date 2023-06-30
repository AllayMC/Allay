package cn.allay.api.world;

import cn.allay.api.math.location.Loc;

/**
 * Describe a world
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface World {
    String getName();

    DimensionInfo getDimensionInfo();

    Loc<Float> getSpawnLocation();

    Difficulty getDifficulty();
}
