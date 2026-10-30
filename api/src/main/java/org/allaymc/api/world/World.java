package org.allaymc.api.world;

import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.storage.WorldStorage;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Map;

/**
 * {@code World} exposes a loaded world's dimensions, persistent metadata,
 * scheduler, and lifecycle state. A world owns shared time and weather while
 * dimensions provide spatial simulation. Callers schedule world-level
 * mutations through its scheduler and use {@link #getState()} to inspect
 * lifecycle progress.
 *
 * @author daoge_cmd
 */
public interface World extends TaskCreator {

    /**
     * Returns the stable pool key identifying this world instance.
     */
    String getName();

    /**
     * Returns the thread assigned to run this world's loop.
     */
    Thread getWorldThread();

    /**
     * Returns the storage backend assigned to this world instance.
     */
    WorldStorage getWorldStorage();

    /**
     * Returns whether this world selects virtual threads for its simulation
     * loops.
     */
    boolean isVirtualTickingThread();

    /**
     * Returns the loop counter initialized from persisted total world time.
     */
    long getTick();

    /**
     * Returns the recent tick callback capacity estimate, capped at the target
     * rate. The estimate uses callback duration rather than counting completed
     * ticks over elapsed wall time.
     */
    float getTPS();

    /**
     * Returns the recent mean tick callback duration in milliseconds. Loop
     * parking and idle callbacks are excluded, and concurrent reads can
     * observe a sample window while it changes.
     */
    float getMSPT();

    /**
     * Returns mean tick callback duration divided by the configured tick
     * budget. The ratio excludes loop parking and idle work and can exceed
     * {@code 1} for overloaded ticks.
     */
    float getTickUsage();

    /**
     * Returns the loaded dimension for the supplied registered dimension type.
     * A {@code null} result means this world has no dimension of that type;
     * registry membership alone does not load it.
     */
    Dimension getDimension(DimensionType dimensionType);

    /**
     * Returns a loaded dimension after resolving its registered identifier. An
     * unregistered identifier or a registered type absent from this world
     * produces {@code null}, allowing callers to distinguish lookup failure
     * from a loaded dimension.
     */
    default Dimension getDimension(org.allaymc.api.utils.identifier.Identifier dimensionIdentifier) {
        var dimensionType = Registries.DIMENSIONS.getByK2(dimensionIdentifier);
        return dimensionType == null ? null : getDimension(dimensionType);
    }

    /**
     * Returns a loaded dimension after resolving its registered numeric ID. An
     * unregistered ID or a registered type absent from this world produces
     * {@code null}; this lookup does not create dimensions.
     */
    default Dimension getDimension(int dimensionId) {
        var dimensionType = Registries.DIMENSIONS.getByK1(dimensionId);
        return dimensionType == null ? null : getDimension(dimensionType);
    }

    /**
     * Returns the overworld dimension required by normal world loading.
     */
    default Dimension getOverWorld() {
        return getDimension(DimensionTypes.OVERWORLD);
    }

    /**
     * Returns this world's nether dimension when that type is loaded. The
     * result is {@code null} when world configuration disables the nether or
     * the dimension has not been attached.
     */
    default Dimension getNether() {
        return getDimension(DimensionTypes.NETHER);
    }

    /**
     * Returns this world's end dimension when that type is loaded. The result
     * is {@code null} when world configuration disables the end or the
     * dimension has not been attached.
     */
    default Dimension getTheEnd() {
        return getDimension(DimensionTypes.THE_END);
    }

    /**
     * Returns an unmodifiable view of this world's dimension mapping. The view
     * retains the same dimension instances and reflects changes to the
     * underlying mapping rather than copying dimensions.
     */
    @UnmodifiableView
    Map<DimensionType, Dimension> getDimensions();

    /**
     * Returns an unmodifiable collection of players across this world's
     * dimensions. The collection contains the existing player references;
     * retaining it does not track later membership changes in those
     * dimensions.
     */
    @UnmodifiableView
    Collection<Player> getPlayers();

    /**
     * Returns the scheduler advanced by this world's tick callback.
     */
    Scheduler getScheduler();

    /**
     * Returns the shared mutable metadata associated with this world. Callers
     * must coordinate mutations with world execution and use the metadata
     * setters when changes require events or client notifications.
     */
    WorldData getWorldData();

    /**
     * Dispatches the metadata save event and invokes this world's storage
     * writer. Storage implementations determine when the bytes become
     * persistent, so return from this method does not universally establish
     * durable completion.
     */
    void saveWorldData();

    /**
     * Returns the currently published lifecycle phase for this world.
     */
    WorldState getState();

    /**
     * Returns the weather currently selected for this world's players.
     */
    Weather getWeather();

    /**
     * Requests a weather replacement through the cancellable weather event. An
     * unchanged value skips dispatch; an accepted change updates the world and
     * sends the resulting weather to its players.
     */
    void setWeather(Weather weather);

    /**
     * Sets the countdown before the world next attempts day advancement.
     * Positive values count down during eligible sleep ticks; nonpositive
     * values leave no active countdown, and later eligibility is checked when
     * it expires.
     */
    void setRequiredSleepTicks(int ticks);

    /**
     * Requests clear weather through the normal cancellable world weather
     * setter. Listeners may reject the change, and an already clear world
     * skips the event and further player notifications.
     */
    default void clearWeather() {
        setWeather(Weather.CLEAR);
    }

    /**
     * Returns a new location combining world spawn coordinates with the
     * overworld.
     */
    default Location3dc getSpawnPoint() {
        var vec = getWorldData().getSpawnPoint();
        return new Location3d(vec.x(), vec.y(), vec.z(), getOverWorld());
    }

    /**
     * Returns whether shutdown omits this world from saved startup
     * configuration. The flag controls future automatic loading; the world's
     * storage and ordinary metadata or chunk writes still operate.
     */
    boolean isRuntimeOnly();

    /**
     * Sets whether shutdown omits this world from saved startup configuration.
     * The flag applies when the pool rewrites configuration and does not
     * delete world storage or disable normal persistence.
     */
    void setRuntimeOnly(boolean runtimeOnly);

    /**
     * Returns whether this world currently accepts creator-bound scheduled
     * tasks.
     */
    @Override
    default boolean isValid() {
        return getState() == WorldState.RUNNING;
    }
}
