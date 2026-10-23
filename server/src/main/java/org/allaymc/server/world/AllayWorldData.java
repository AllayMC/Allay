package org.allaymc.server.world;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.world.DifficultyChangeEvent;
import org.allaymc.api.eventbus.event.world.GameRuleChangeEvent;
import org.allaymc.api.eventbus.event.world.SpawnPointChangeEvent;
import org.allaymc.api.eventbus.event.world.TimeChangeEvent;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.AllayServer;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code AllayWorldData} stores a world's mutable metadata, including its
 * clocks, spawn coordinates, player defaults, and persistent data container.
 * The generated builder supplies defaults for omitted values, while {@code
 * setWorld} binds the game rule container to the same world. Event-aware
 * setters apply listener-selected replacements only after accepted dispatch;
 * callers coordinate mutations with world execution.
 */
@Getter
@Builder
public final class AllayWorldData implements WorldData {

    /**
     * {@code world} identifies the owner used by event dispatch and player
     * notifications; {@code setWorld} also binds {@code gameRules} to that
     * owner.
     */
    private World world;

    /**
     * Difficulty selected from stored metadata or server defaults.
     */
    @Builder.Default
    private Difficulty difficulty = AllayServer.getSettings().genericSettings().defaultDifficulty();
    /**
     * Default mode for this world's players.
     */
    @Builder.Default
    private GameMode gameMode = AllayServer.getSettings().genericSettings().defaultGameMode();
    /**
     * Display label stored separately from the world name.
     */
    @Setter
    @Builder.Default
    private String displayName = DEFAULT_WORLD_DISPLAY_NAME;
    /**
     * {@code spawnPoint} retains the accepted overworld coordinates without
     * copying; the generated getter exposes that same reference to callers.
     */
    @Builder.Default
    private Vector3ic spawnPoint = new Vector3i(0, 64, 0);
    /**
     * World loop counter in persisted game ticks.
     */
    @Builder.Default
    @Setter
    private long totalTime = 0L;
    /**
     * Independent day-cycle position in game ticks.
     */
    @Builder.Default
    private int timeOfDay = TIME_SUNRISE;
    /**
     * {@code gameRules} holds the shared mutable rule container that {@code
     * setWorld} binds before mutations can notify players.
     */
    @Builder.Default
    private AllayGameRules gameRules = new AllayGameRules();
    /**
     * Persisted count of world instance initializations.
     */
    @Getter
    @Builder.Default
    private long worldStartCount = 0;
    /**
     * {@code persistentDataContainer} holds mutable plugin metadata; its
     * generated accessors expose and replace the same container without
     * copying its entries.
     */
    @Getter
    @Setter
    @Builder.Default
    private PersistentDataContainer persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);

    /**
     * Binds this metadata and its game rule container to the supplied world.
     * Event-aware setters and direct game rule notifications then use that
     * world; callers establish the binding before those operations.
     */
    public void setWorld(World world) {
        this.world = world;
        this.gameRules.setWorld(world);
    }

    /**
     * Replaces the stored default game mode for this world.
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Proposes a difficulty replacement through the cancellable world event.
     * Accepted dispatch stores {@code newDifficulty} from the event;
     * cancellation preserves the previous value and no player broadcast occurs
     * here.
     */
    @Override
    public void setDifficulty(Difficulty difficulty) {
        var event = new DifficultyChangeEvent(this.world, this.difficulty, difficulty);
        if (event.call()) {
            this.difficulty = event.getNewDifficulty();
        }
    }

    /**
     * Normalizes the requested time, dispatches its event, and broadcasts an
     * accepted replacement. Out-of-range inputs select {@code TIME_DAY};
     * listeners may supply another valid time, while cancellation preserves
     * the current value without broadcasting.
     */
    @Override
    public void setTimeOfDay(int timeOfDay) {
        timeOfDay = rollbackTimeOfDay(timeOfDay);

        var event = new TimeChangeEvent(this.world, this.timeOfDay, timeOfDay);
        if (!event.call()) {
            return;
        }

        this.timeOfDay = event.getNewTime();
        this.world.getPlayers().forEach(player -> player.viewTime(this.world.getWorldData().getTimeOfDay()));
    }

    /**
     * Returns the supplied time when it lies within the inclusive day bounds.
     * Out-of-range values select {@code TIME_DAY}, so this helper resets
     * overflow instead of preserving a remainder with modulo arithmetic.
     */
    private int rollbackTimeOfDay(int time) {
        if (time < TIME_DAY || time > TIME_FULL) {
            return TIME_DAY;
        }
        return time;
    }

    /**
     * Proposes a game rule replacement through its cancellable world event.
     * Accepted dispatch passes the listener-selected value to the shared
     * container, which stores it before notifying players and does not
     * validate its type.
     */
    @Override
    public void setGameRuleValue(GameRule gameRule, Object value) {
        var event = new GameRuleChangeEvent(this.world, gameRule, getGameRuleValue(gameRule), value);
        if (event.call()) {
            this.gameRules.set(gameRule, event.getNewValue());
        }
    }

    /**
     * Returns a stored game rule value or its declared default when absent.
     * The caller-selected result type must match the stored value because this
     * operation delegates an unchecked generic lookup.
     */
    @Override
    public <V> V getGameRuleValue(GameRule gameRule) {
        return gameRules.get(gameRule);
    }

    /**
     * Proposes a new spawn reference through the cancellable world event.
     * Accepted dispatch retains {@code newPos} without copying, so callers and
     * listeners must leave the selected coordinate object unchanged through
     * mutable aliases.
     */
    @Override
    public void setSpawnPoint(Vector3ic spawnPoint) {
        var event = new SpawnPointChangeEvent(this.world, this.spawnPoint, spawnPoint);
        if (event.call()) {
            this.spawnPoint = event.getNewPos();
        }
    }

    /**
     * Increments the persisted initialization count when a world instance is
     * created.
     */
    public void increaseWorldStartCount() {
        this.worldStartCount++;
    }
}
