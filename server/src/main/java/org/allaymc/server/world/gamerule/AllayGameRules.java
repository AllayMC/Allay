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
 * @author daoge_cmd
 */
public class AllayGameRules implements GameRules {

    private final Map<GameRule, Object> gameRules = new HashMap<>();

    /**
     * The world that these game rules are applied to.
     */
    @Setter
    private World world;

    /**
     * Creates a new GameRules instance with default values.
     */
    public AllayGameRules() {
        for (GameRule gameRule : GameRule.values()) {
            this.gameRules.put(gameRule, gameRule.getDefaultValue());
        }
    }

    /**
     * Creates a new GameRules instance with the specified game rules.
     *
     * @param gameRules the game rules to be added
     */
    public AllayGameRules(Map<GameRule, Object> gameRules) {
        this.gameRules.putAll(gameRules);
    }

    /**
     * Reads game rules from NBT.
     *
     * @param nbt the NBT to read from
     * @return the game rules
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

    @Override
    @UnmodifiableView
    public Map<GameRule, Object> getGameRules() {
        return Collections.unmodifiableMap(gameRules);
    }

    @Override
    public void set(GameRule gameRule, Object value) {
        this.gameRules.put(gameRule, value);
        this.world.getPlayers().forEach(player -> player.viewGameRules(this));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> V get(GameRule gameRule) {
        return (V) gameRules.getOrDefault(gameRule, gameRule.getDefaultValue());
    }

    /**
     * Writes the game rules to NBT.
     *
     * @param builder the NbtMapBuilder to write to
     */
    public void writeToNBT(NbtMapBuilder builder) {
        for (Map.Entry<GameRule, Object> entry : this.gameRules.entrySet()) {
            // Lower case name should be used for key
            var key = entry.getKey().getName().toLowerCase(Locale.ROOT);
            switch (entry.getKey().getType()) {
                case INT -> builder.putInt(key, (Integer) entry.getValue());
                case BOOLEAN -> builder.putBoolean(key, (Boolean) entry.getValue());
            }
        }
    }
}
