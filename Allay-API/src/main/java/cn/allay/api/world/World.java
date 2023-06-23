package cn.allay.api.world;

import cn.allay.api.math.location.Loc;

/**
 * Describe a world
 * <p>
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 */
public interface World {
    String getName();

    DimensionInfo getDimensionInfo();

    Loc<Float> getSpawnLocation();

    Difficulty getDifficulty();
}
