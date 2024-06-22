package org.allaymc.api.world.gamerule;

import org.allaymc.api.world.World;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Allay Project 2023/3/4
 *
 * @author Jukebox | Cool_Loong
 */
public class GameRules {
    public static final GameRules DEFAULT = new GameRules();

    private final Map<GameRule, Object> gameRules = new HashMap<>();

    private boolean dirty;

    public GameRules() {
        for (GameRule gameRule : GameRule.values()) {
            this.gameRules.put(gameRule, gameRule.getDefaultValue());
        }
    }

    public GameRules(Map<GameRule, Object> gameRules) {
        this.gameRules.putAll(gameRules);
    }

    @UnmodifiableView
    public Map<GameRule, Object> getGameRules() {
        return Collections.unmodifiableMap(gameRules);
    }

    public void put(GameRule gameRule, Object o) {
        gameRules.put(gameRule, o);
        dirty = true;
    }

    @SuppressWarnings("unchecked")
    public <V> V get(GameRule gameRule) {
        return (V) gameRules.getOrDefault(gameRule, gameRule.getDefaultValue());
    }

    public void sync(World world) {
        if (!dirty) return;
        world.broadcastPacket(buildPacket());
        dirty = false;
    }

    public GameRulesChangedPacket buildPacket() {
        var pk = new GameRulesChangedPacket();
        pk.getGameRules().addAll(toNetworkGameRuleData());
        return pk;
    }

    public List<GameRuleData<?>> toNetworkGameRuleData() {
        return this.getGameRules().entrySet().stream()
                .map(entry -> new GameRuleData<>(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
