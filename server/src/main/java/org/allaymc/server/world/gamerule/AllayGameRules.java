package org.allaymc.server.world.gamerule;

import lombok.Setter;
import org.allaymc.api.world.World;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * {@code AllayGameRules} stores world game rule values and translates explicit
 * entries to and from NBT. Missing entries use {@link
 * GameRule#getDefaultValue()} during lookup without being inserted into the
 * stored map. The world binding enables player notifications after direct
 * mutations; callers serialize changes with world execution.
 *
 * @author daoge_cmd
 */
public class AllayGameRules implements GameRules {

    /**
     * {@code gameRules} owns the mutable entry map; returned unmodifiable
     * views remain backed by this map and reflect later replacements.
     */
    private final Map<GameRule, Object> gameRules = new HashMap<>();

    /**
     * {@code world} supplies players notified by {@code set}; callers must
     * bind a world before using that mutation operation.
     */
    @Setter
    private World world;

    /**
     * Creates a rule container with every supported default explicitly stored.
     */
    public AllayGameRules() {
        for (GameRule gameRule : GameRule.values()) {
            this.gameRules.put(gameRule, gameRule.getDefaultValue());
        }
    }

    /**
     * Creates a container by copying the supplied map's entries. Missing rules
     * stay absent and use defaults during lookup; the constructor does not
     * validate supplied value types or bind a world.
     */
    public AllayGameRules(Map<GameRule, Object> gameRules) {
        this.gameRules.putAll(gameRules);
    }

    /**
     * Reads supported rules whose lower-case canonical keys occur in the NBT
     * map. Missing entries stay absent and use rule defaults during lookup;
     * the returned container must be bound before mutation notifications.
     */
    public static AllayGameRules readFromNBT(NbtMap nbt) {
        Map<GameRule, Object> gameRules = new HashMap<>();
        for (GameRule gameRule : GameRule.values()) {
            var key = gameRule.getName().toLowerCase(Locale.ROOT);
            if (nbt.containsKey(key)) {
                switch (gameRule.getType()) {
                    case INT -> gameRules.put(gameRule, nbt.getInt(key));
                    case BOOLEAN -> gameRules.put(gameRule, nbt.getBoolean(key));
                }
            }
        }
        return new AllayGameRules(gameRules);
    }

    /**
     * Returns an unmodifiable view backed by the stored rule map. The view
     * exposes explicit entries only, so rules supplied by lookup defaults need
     * not appear in it.
     */
    @Override
    @UnmodifiableView
    public Map<GameRule, Object> getGameRules() {
        return Collections.unmodifiableMap(gameRules);
    }

    /**
     * Stores the supplied rule value before notifying the bound world's
     * players. Callers must bind {@code world} and supply the expected value
     * type; a notification failure does not undo the map replacement.
     */
    @Override
    public void set(GameRule gameRule, Object value) {
        this.gameRules.put(gameRule, value);
        this.world.getPlayers().forEach(player -> player.viewGameRules(this));
    }

    /**
     * Returns the explicit entry or the rule default when no entry exists. The
     * unchecked cast leaves type consistency to callers; a stored {@code null}
     * remains {@code null} rather than selecting the default.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <V> V get(GameRule gameRule) {
        return (V) gameRules.getOrDefault(gameRule, gameRule.getDefaultValue());
    }

    /**
     * Writes explicitly stored entries under lower-case canonical rule names.
     * Missing entries are omitted, and incorrect value types or {@code null}
     * values can fail conversion while earlier entries remain in the supplied
     * builder.
     */
    public void writeToNBT(NbtMapBuilder builder) {
        for (Map.Entry<GameRule, Object> entry : this.gameRules.entrySet()) {
            // NBT uses lower-case keys; preserve the same spelling that readFromNBT
            // expects so stored values are found on the next metadata load.
            var key = entry.getKey().getName().toLowerCase(Locale.ROOT);
            switch (entry.getKey().getType()) {
                case INT -> builder.putInt(key, (Integer) entry.getValue());
                case BOOLEAN -> builder.putBoolean(key, (Boolean) entry.getValue());
            }
        }
    }
}
