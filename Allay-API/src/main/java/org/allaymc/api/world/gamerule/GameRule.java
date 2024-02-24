package org.allaymc.api.world.gamerule;

import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;

import java.util.Arrays;

/**
 * Allay Project 2023/3/4
 *
 * @author Jukebox | Cool_Loong
 */
@Getter
public enum GameRule {

    COMMAND_BLOCKS_ENABLED("commandBlocksEnabled", true, Type.BOOLEAN),
    COMMAND_BLOCK_OUTPUT("commandBlockOutput", true, Type.BOOLEAN),
    DO_DAYLIGHT_CYCLE("doDaylightCycle", true, Type.BOOLEAN), //Implemented
    DO_ENTITY_DROPS("doEntityDrops", true, Type.BOOLEAN),
    DO_FIRE_TICK("doFireTick", true, Type.BOOLEAN),
    DO_INSOMNIA("doInsomnia", true, Type.BOOLEAN),
    DO_IMMEDIATE_RESPAWN("doImmediateRespawn", false, Type.BOOLEAN),
    DO_MOB_LOOT("doMobLoot", true, Type.BOOLEAN),
    DO_MOB_SPAWNING("doMobSpawning", true, Type.BOOLEAN),
    DO_TILE_DROPS("doTileDrops", true, Type.BOOLEAN),
    DO_WEATHER_CYCLE("doWeatherCycle", true, Type.BOOLEAN),
    DROWNING_DAMAGE("drowningDamage", true, Type.BOOLEAN),
    FALL_DAMAGE("fallDamage", true, Type.BOOLEAN),
    FIRE_DAMAGE("fireDamage", true, Type.BOOLEAN),
    KEEP_INVENTORY("keepInventory", false, Type.BOOLEAN),
    MAX_COMMAND_CHAIN_LENGTH("maxCommandChainLength", 65536, Type.INT),
    MOB_GRIEFING("mobGriefing", true, Type.BOOLEAN),
    NATURAL_REGENERATION("naturalRegeneration", true, Type.BOOLEAN),
    PVP("pvp", true, Type.BOOLEAN),
    RANDOM_TICK_SPEED("randomTickSpeed", 1, Type.INT),
    SEND_COMMAND_FEEDBACK("sendCommandFeedback", true, Type.BOOLEAN),
    SHOW_COORDINATES("showCoordinates", false, Type.BOOLEAN),
    SHOW_DEATH_MESSAGES("showDeathMessages", true, Type.BOOLEAN),
    SPAWN_RADIUS("spawnRadius", 5, Type.INT),
    TNT_EXPLODES("tntExplodes", true, Type.BOOLEAN),
    SHOW_TAGS("showTags", true, Type.BOOLEAN),
    FREEZE_DAMAGE("freezeDamage", true, Type.BOOLEAN),
    RESPAWN_BLOCKS_EXPLODE("respawnBlocksExplode", true, Type.BOOLEAN),
    SHOW_BORDER_EFFECT("showBorderEffect", true, Type.BOOLEAN),
    FUNCTION_COMMAND_LIMIT("functionCommandLimit", 10000, Type.INT),
    DO_LIMITED_CRAFTING("dolimitedcrafting", false, Type.BOOLEAN);

    private final String name;
    private final Object defaultValue;
    private final Type type;

    GameRule(String name, Object defaultValue, Type type) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }


    public static GameRule fromName(String name) {
        return Arrays.stream(values()).filter(gameRule -> gameRule.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static boolean parseByteToBoolean(byte value) {
        return value == 1;
    }

    public GameRuleData<?> toNetwork() {
        return new GameRuleData<>(name, defaultValue);
    }

    public <T> GameRuleData<T> toNetwork(T value) {
        return new GameRuleData<>(name, value);
    }

    public enum Type {
        INT,
        BOOLEAN
    }
}
