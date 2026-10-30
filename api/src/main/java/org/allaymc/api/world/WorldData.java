package org.allaymc.api.world;

import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.joml.Vector3ic;

/**
 * {@code WorldData} exposes a world's persisted settings, clocks, spawn
 * coordinates, and game rules. Time of day is separate from total elapsed
 * ticks, and event-aware setters can reject or replace requested values.
 * Callers coordinate mutations with the owning world rather than treating this
 * metadata as independently synchronized. The {@code TIME_DAY}, {@code
 * TIME_NOON}, {@code TIME_SUNSET}, {@code TIME_NIGHT}, {@code TIME_MIDNIGHT},
 * {@code TIME_SUNRISE}, and {@code TIME_FULL} constants describe day-cycle
 * positions in ticks; {@code TIME_SLEEP}, {@code TIME_WAKE}, {@code
 * TIME_SLEEP_WITH_RAIN}, and {@code TIME_WAKE_WITH_RAIN} bound the world's
 * weather-dependent sleep checks.
 *
 * @author daoge_cmd
 */
public interface WorldData extends PersistentDataHolder {

    /**
     * {@code DEFAULT_WORLD_DISPLAY_NAME} supplies the initial display label
     * when stored metadata does not provide another world title.
     */
    String DEFAULT_WORLD_DISPLAY_NAME = "Bedrock level";

    /**
     * Start of the day cycle in ticks.
     */
    int TIME_DAY = 0;
    /**
     * Noon position within the day cycle.
     */
    int TIME_NOON = 6000;
    /**
     * Sunset position within the day cycle.
     */
    int TIME_SUNSET = 12000;
    /**
     * Night position within the day cycle.
     */
    int TIME_NIGHT = 14000;
    /**
     * Midnight position within the day cycle.
     */
    int TIME_MIDNIGHT = 18000;
    /**
     * Sunrise position within the day cycle.
     */
    int TIME_SUNRISE = 23000;
    /**
     * Complete day length in ticks.
     */
    int TIME_FULL = 24000;

    /**
     * Lower clear-weather sleep threshold in ticks.
     */
    int TIME_SLEEP = 12542;
    /**
     * Upper clear-weather sleep threshold in ticks.
     */
    int TIME_WAKE = 23459;
    /**
     * Lower rainy-weather sleep threshold in ticks.
     */
    int TIME_SLEEP_WITH_RAIN = 12010;
    /**
     * Upper rainy-weather sleep threshold in ticks.
     */
    int TIME_WAKE_WITH_RAIN = 23991;

    /**
     * Returns the difficulty currently stored in this world's metadata.
     */
    Difficulty getDifficulty();

    /**
     * Proposes a difficulty change through the world's cancellable difficulty
     * event. Accepted dispatch stores the listener-selected replacement;
     * cancellation preserves the previous difficulty rather than applying the
     * requested value.
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Returns the default game mode stored for this world.
     */
    GameMode getGameMode();

    /**
     * Replaces the default game mode stored for this world.
     */
    void setGameMode(GameMode gameMode);

    /**
     * Returns the display label stored in this world's metadata. The label may
     * differ from {@link World#getName()}, which identifies the world in the
     * pool and startup configuration.
     */
    String getDisplayName();

    /**
     * Replaces the display label stored in this world's metadata.
     */
    void setDisplayName(String name);

    /**
     * Returns the stored spawn coordinate reference in the overworld. The
     * coordinates are exposed without a copy, so callers must use the setter
     * instead of mutating a retained mutable alias.
     */
    Vector3ic getSpawnPoint();

    /**
     * Proposes overworld spawn coordinates through the cancellable spawn
     * event. Accepted dispatch retains the selected coordinate reference
     * without copying; callers must avoid changing that object afterward
     * through mutable aliases.
     */
    void setSpawnPoint(Vector3ic spawnPoint);

    /**
     * Returns the separately stored position within the day cycle. This value
     * can be changed independently of {@link #getTotalTime()}, so it is not
     * derived from total elapsed ticks.
     */
    int getTimeOfDay();

    /**
     * Proposes a time of day and broadcasts an accepted event result. Values
     * outside {@code TIME_DAY} through {@code TIME_FULL}, inclusive, select
     * {@code TIME_DAY} before dispatch; cancellation preserves the previous
     * time.
     */
    void setTimeOfDay(int timeOfDay);

    /**
     * Adds ticks to the current time through {@link #setTimeOfDay(int)}. The
     * setter supplies range handling and event cancellation; this helper does
     * not apply modulo arithmetic to the sum.
     */
    default void addTimeOfDay(int amount) {
        setTimeOfDay(getTimeOfDay() + amount);
    }

    /**
     * Returns the persisted world loop counter in game ticks. The counter
     * spans multiple day cycles and is independent of changes to the stored
     * time of day.
     */
    long getTotalTime();

    /**
     * Returns the selected rule value, using its declared default when absent.
     * Callers select {@code V} to match {@code GameRule.getType()}; the
     * generic result does not validate that choice at runtime.
     */
    <V> V getGameRuleValue(GameRule gameRule);

    /**
     * Proposes a rule replacement through the cancellable game rule event.
     * Accepted dispatch stores the listener-selected value and notifies
     * players; callers and listeners must supply the value type declared by
     * {@code gameRule}.
     */
    void setGameRuleValue(GameRule gameRule, Object value);

    /**
     * Returns the shared game rule container associated with this metadata.
     * Direct container mutation follows that container's contract and bypasses
     * the cancellable event supplied by {@link #setGameRuleValue(GameRule,
     * Object)}.
     */
    GameRules getGameRules();

    /**
     * Returns the persisted count incremented when a world instance is
     * initialized.
     */
    long getWorldStartCount();
}
