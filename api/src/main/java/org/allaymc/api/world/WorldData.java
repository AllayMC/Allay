package org.allaymc.api.world;

import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.joml.Vector3ic;

/**
 * The information for a world, usually shouldn't be edited by the user.
 *
 * @author daoge_cmd
 */
public interface WorldData extends PersistentDataHolder {

    String DEFAULT_WORLD_DISPLAY_NAME = "Bedrock level";

    int TIME_DAY = 0;
    int TIME_NOON = 6000;
    int TIME_SUNSET = 12000;
    int TIME_NIGHT = 14000;
    int TIME_MIDNIGHT = 18000;
    int TIME_SUNRISE = 23000;
    int TIME_FULL = 24000;

    int TIME_SLEEP = 12542;
    int TIME_WAKE = 23459;
    int TIME_SLEEP_WITH_RAIN = 12010;
    int TIME_WAKE_WITH_RAIN = 23991;

    /**
     * Get the difficulty of the world.
     *
     * @return the difficulty
     */
    Difficulty getDifficulty();

    /**
     * Set the difficulty of the world.
     *
     * @param difficulty the difficulty to set
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Get the game mode of the world.
     *
     * @return the game mode
     */
    GameMode getGameMode();

    /**
     * Set the game mode of the world.
     *
     * @param gameMode the game mode to set
     */
    void setGameMode(GameMode gameMode);

    /**
     * Get the display name of the world.
     * <p>
     * This field is just the display name of the world, which is
     * not equal to the name in world-settings.yml
     *
     * @return the name of the world
     */
    String getDisplayName();

    /**
     * Set the display name of the world.
     *
     * @param name the display name to set
     */
    void setDisplayName(String name);

    /**
     * Get the spawn point of the world.
     *
     * @return the spawn point of the world
     */
    Vector3ic getSpawnPoint();

    /**
     * Set the spawn point of the world.
     *
     * @param spawnPoint the spawn point of the world to set
     */
    void setSpawnPoint(Vector3ic spawnPoint);

    /**
     * Get the time of day.
     * <p>
     * This is equal to {@code getTotalTime() % TIME_FULL}
     *
     * @return the time of day
     */
    int getTimeOfDay();

    /**
     * Set the time of day.
     *
     * @param timeOfDay the time
     */
    void setTimeOfDay(int timeOfDay);

    /**
     * Add a certain amount of time to the time of the day.
     *
     * @param amount thr amount of time to add
     */
    default void addTimeOfDay(int amount) {
        setTimeOfDay(getTimeOfDay() + amount);
    }

    /**
     * Get the time that has been passed since the first time the world is created (unit: gt).
     * <p>
     * Please note that the value of this field can be bigger than 24000 (the length of a day, 20min)
     *
     * @return the total time
     */
    long getTotalTime();

    /**
     * Get the value of a certain game rule.
     *
     * @param gameRule the game rule
     * @return the value of the game rule provided
     */
    <V> V getGameRuleValue(GameRule gameRule);

    /**
     * Set the value of a certain game rule.
     *
     * @param gameRule the game rule to set
     * @param value    the new value
     */
    void setGameRuleValue(GameRule gameRule, Object value);

    /**
     * Get all game rules of this world.
     *
     * @return all game rules of this world
     */
    GameRules getGameRules();

    /**
     * Get the start count of the world.
     * <p>
     * Determines how many times the world has been closed since the world was created,
     * with its value decreasing by 1 each time.
     *
     * @return the start count of the world
     */
    long getWorldStartCount();
}
