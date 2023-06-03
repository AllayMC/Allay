package cn.allay.api.world;

import cn.allay.api.math.vector.Vec3;
import cn.allay.api.world.dimension.DimensionData;
import cn.allay.api.world.gamerule.GameRules;
import org.cloudburstmc.nbt.NbtMap;

import java.io.File;

/**
 * Author: Cool_Loong <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
public interface WorldData {
    File getFile();

    NbtMap getNBT();

    /**
     * be: RandomSeed
     * je: WorldGenSettings#seed
     */
    long getRandomSeed();

    /**
     * be: SpawnX SpawnY SpawnZ
     * je: SpawnX SpawnY SpawnZ
     */
    Vec3<Integer> getSpawnPoint();

    /**
     * be: rainTime
     * je: rainTime
     */
    int getRainTime();

    /**
     * be: lightningTime
     * je: thunderTime
     */
    int getThunderTime();

    /**
     * be: LevelName
     * je: LevelName
     */
    String getWorldName();

    /**
     * be: Time
     * je: Time
     */
    long getTime();

    /**
     * be: Difficulty
     * je: Difficulty
     */
    int getDifficulty();

    /**
     * be: GameType
     * je: GameType
     */
    int getGameType();

    GameRules getGameRules();

    DimensionData getDimensionData();
}
