package org.allaymc.api.world.gamerule;

import org.allaymc.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GameRules is a container for storing and managing game rules.
 *
 * @author Jukebox | Cool_Loong
 */
public class GameRules {
    public static final GameRules DEFAULT = new GameRules();

    private final Map<GameRule, Object> gameRules = new HashMap<>();

    /**
     * Whether the game rules have been modified since last sending to the players.
     */
    private boolean dirty;

    /**
     * Creates a new GameRules instance with default values.
     */
    public GameRules() {
        for (GameRule gameRule : GameRule.values()) {
            this.gameRules.put(gameRule, gameRule.getDefaultValue());
        }
    }

    /**
     * Creates a new GameRules instance with the specified game rules.
     *
     * @param gameRules the game rules to be added
     */
    public GameRules(Map<GameRule, Object> gameRules) {
        this.gameRules.putAll(gameRules);
    }

    /**
     * Reads game rules from NBT.
     *
     * @param nbt the NBT to read from
     *
     * @return the game rules
     */
    public static GameRules readFromNBT(NbtMap nbt) {
        Map<GameRule, Object> gameRules = new HashMap<>();
        for (GameRule gameRule : GameRule.values()) {
            var key = gameRule.getName().toLowerCase();
            if (nbt.containsKey(key)) {
                switch (gameRule.getType()) {
                    case INT -> gameRules.put(gameRule, nbt.getInt(key));
                    case BOOLEAN -> gameRules.put(gameRule, nbt.getBoolean(key));
                }
            }
        }
        return new GameRules(gameRules);
    }

    /**
     * Get the game rules.
     *
     * @return the game rules
     */
    @UnmodifiableView
    public Map<GameRule, Object> getGameRules() {
        return Collections.unmodifiableMap(gameRules);
    }

    /**
     * Set a game rule's value.
     *
     * @param gameRule the game rule
     * @param value    the value
     */
    public void put(GameRule gameRule, Object value) {
        gameRules.put(gameRule, value);
        dirty = true;
    }

    /**
     * Get a game rule's value.
     *
     * @param gameRule the game rule
     * @param <V>      the type of the value
     *
     * @return the value
     */
    @SuppressWarnings("unchecked")
    public <V> V get(GameRule gameRule) {
        return (V) gameRules.getOrDefault(gameRule, gameRule.getDefaultValue());
    }

    /**
     * Send the game rules to the players in a world.
     *
     * @param world the world which players are in.
     */
    public void sync(World world) {
        if (!dirty) return;
        world.broadcastPacket(buildPacket());
        dirty = false;
    }

    /**
     * Builds a GameRulesChangedPacket from the game rules.
     *
     * @return the packet
     */
    public GameRulesChangedPacket buildPacket() {
        var pk = new GameRulesChangedPacket();
        pk.getGameRules().addAll(toNetworkGameRuleData());
        return pk;
    }

    /**
     * Converts the game rules to a list of GameRuleData.
     *
     * @return the list of GameRuleData
     */
    public List<GameRuleData<?>> toNetworkGameRuleData() {
        return this.getGameRules().entrySet().stream()
                .map(entry -> new GameRuleData<>(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Writes the game rules to NBT.
     *
     * @param builder the NbtMapBuilder to write to
     */
    public void writeToNBT(NbtMapBuilder builder) {
        for (Map.Entry<GameRule, Object> entry : this.gameRules.entrySet()) {
            // Lower case name should be used for key
            var key = entry.getKey().getName().toLowerCase();
            switch (entry.getKey().getType()) {
                case INT -> builder.putInt(key, (Integer) entry.getValue());
                case BOOLEAN -> builder.putBoolean(key, (Boolean) entry.getValue());
            }
        }
    }
}
