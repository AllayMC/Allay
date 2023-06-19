package cn.allay.api.world.gamerule;

import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.packet.GameRulesChangedPacket;

import java.util.*;

/**
 * Author: Jukebox | Cool_Loong <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class GameRules {

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

    public List<GameRuleData<?>> getGameRules() {
        final Set<Map.Entry<GameRule, Object>> entrySet = this.gameRules.entrySet();
        final List<GameRuleData<?>> networkList = new ArrayList<>(entrySet.size());
        for (Map.Entry<GameRule, Object> entry : entrySet) {
            networkList.add(new GameRuleData<>(entry.getKey().getName().toString(), entry.getValue()));
        }
        return networkList;
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
        this.changedPacket.getGameRules().addAll(this.getGameRules());
        return this.changedPacket;
    }

}
