package cn.allay.api.world.gamerule;

import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * Allay Project 2023/3/4
 *
 * @author Jukebox | Cool_Loong
 */
public class GameRules {
    public static final GameRules DEFAULT = new GameRules();

    private final Map<GameRule, Object> gameRules = new HashMap<>();

    private GameRulesChangedPacket changedPacket;

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
        this.gameRules.put(gameRule, o);
        this.changedPacket = null;
    }

    @SuppressWarnings("unchecked")
    public <V> V get(GameRule gameRule) {
        return (V) this.gameRules.getOrDefault(gameRule, gameRule.getDefaultValue());
    }

    public boolean requiresUpdate() {
        return this.changedPacket == null;
    }

    public GameRulesChangedPacket updatePacket() {
        if (!this.requiresUpdate()) {
            return this.changedPacket;
        }
        this.changedPacket = new GameRulesChangedPacket();
        List<GameRuleData<?>> gameRuleData = new ArrayList<>();
        for (var entry : this.getGameRules().entrySet()) {
            gameRuleData.add(new GameRuleData<>(entry.getKey().getName(), entry.getValue()));
        }
        this.changedPacket.getGameRules().addAll(gameRuleData);
        return this.changedPacket;
    }

}
