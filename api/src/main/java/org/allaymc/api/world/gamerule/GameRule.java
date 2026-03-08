package org.allaymc.api.world.gamerule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * GameRule represents a game rule in the game
 *
 * @author Jukebox | Cool_Loong
 */
@Getter
@AllArgsConstructor
public enum GameRule {
    COMMAND_BLOCKS_ENABLED("commandBlocksEnabled", true, Type.BOOLEAN),
    COMMAND_BLOCK_OUTPUT("commandBlockOutput", true, Type.BOOLEAN),
    DO_DAYLIGHT_CYCLE("doDaylightCycle", true, Type.BOOLEAN),
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
    TNT_EXPLOSION_DROP_DECAY("tntExplosionDropDecay", false, Type.BOOLEAN),
    SHOW_TAGS("showTags", true, Type.BOOLEAN),
    FREEZE_DAMAGE("freezeDamage", true, Type.BOOLEAN),
    RESPAWN_BLOCKS_EXPLODE("respawnBlocksExplode", true, Type.BOOLEAN),
    SHOW_BORDER_EFFECT("showBorderEffect", true, Type.BOOLEAN),
    SHOW_DAYS_PLAYED("showDaysPlayed", false, Type.BOOLEAN),
    FUNCTION_COMMAND_LIMIT("functionCommandLimit", 10000, Type.INT),
    DO_LIMITED_CRAFTING("dolimitedcrafting", false, Type.BOOLEAN),
    LOCATOR_BAR("locatorBar", true, Type.BOOLEAN),
    PLAYERS_SLEEPING_PERCENTAGE("playersSleepingPercentage", 100, Type.INT);

    private final String name;
    private final Object defaultValue;
    private final Type type;

    /**
     * Get a game rule by name.
     *
     * @param name the name of the game rule
     *
     * @return the game rule, or {@code null} if not found
     */
    public static GameRule fromName(String name) {
        return Arrays.stream(values())
                .filter(gameRule -> gameRule.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public enum Type {
        INT,
        BOOLEAN
    }
}
