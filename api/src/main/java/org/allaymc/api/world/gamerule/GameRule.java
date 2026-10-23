package org.allaymc.api.world.gamerule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * {@code GameRule} identifies a supported world rule together with its network
 * name, default value, and value category. The constants provide metadata for
 * game rule containers and serializers; each consumer decides whether and how
 * the corresponding rule affects gameplay. {@code Type.INT} rules use {@code
 * Integer} values, and {@code Type.BOOLEAN} rules use {@code Boolean} values.
 *
 * @author Jukebox | Cool_Loong
 */
@Getter
@AllArgsConstructor
public enum GameRule {
    /**
     * Command block activation rule.
     */
    COMMAND_BLOCKS_ENABLED("commandBlocksEnabled", true, Type.BOOLEAN),
    /**
     * Command block output rule.
     */
    COMMAND_BLOCK_OUTPUT("commandBlockOutput", true, Type.BOOLEAN),
    /**
     * Day cycle advancement rule.
     */
    DO_DAYLIGHT_CYCLE("doDaylightCycle", true, Type.BOOLEAN),
    /**
     * Entity item drop rule.
     */
    DO_ENTITY_DROPS("doEntityDrops", true, Type.BOOLEAN),
    /**
     * Fire update progression rule.
     */
    DO_FIRE_TICK("doFireTick", true, Type.BOOLEAN),
    /**
     * Insomnia behavior selection rule.
     */
    DO_INSOMNIA("doInsomnia", true, Type.BOOLEAN),
    /**
     * Immediate player respawn rule.
     */
    DO_IMMEDIATE_RESPAWN("doImmediateRespawn", false, Type.BOOLEAN),
    /**
     * Mob loot drop rule.
     */
    DO_MOB_LOOT("doMobLoot", true, Type.BOOLEAN),
    /**
     * Mob spawning permission rule.
     */
    DO_MOB_SPAWNING("doMobSpawning", true, Type.BOOLEAN),
    /**
     * Block item drop rule.
     */
    DO_TILE_DROPS("doTileDrops", true, Type.BOOLEAN),
    /**
     * Weather cycle progression rule.
     */
    DO_WEATHER_CYCLE("doWeatherCycle", true, Type.BOOLEAN),
    /**
     * Drowning damage permission rule.
     */
    DROWNING_DAMAGE("drowningDamage", true, Type.BOOLEAN),
    /**
     * Fall damage permission rule.
     */
    FALL_DAMAGE("fallDamage", true, Type.BOOLEAN),
    /**
     * Fire damage permission rule.
     */
    FIRE_DAMAGE("fireDamage", true, Type.BOOLEAN),
    /**
     * Inventory retention on death.
     */
    KEEP_INVENTORY("keepInventory", false, Type.BOOLEAN),
    /**
     * Command chain execution limit.
     */
    MAX_COMMAND_CHAIN_LENGTH("maxCommandChainLength", 65536, Type.INT),
    /**
     * Mob world modification rule.
     */
    MOB_GRIEFING("mobGriefing", true, Type.BOOLEAN),
    /**
     * Natural health regeneration rule.
     */
    NATURAL_REGENERATION("naturalRegeneration", true, Type.BOOLEAN),
    /**
     * Player versus player damage rule.
     */
    PVP("pvp", true, Type.BOOLEAN),
    /**
     * Random block tick sample count.
     */
    RANDOM_TICK_SPEED("randomTickSpeed", 1, Type.INT),
    /**
     * Command feedback delivery rule.
     */
    SEND_COMMAND_FEEDBACK("sendCommandFeedback", true, Type.BOOLEAN),
    /**
     * Client coordinate display rule.
     */
    SHOW_COORDINATES("showCoordinates", false, Type.BOOLEAN),
    /**
     * Death message display rule.
     */
    SHOW_DEATH_MESSAGES("showDeathMessages", true, Type.BOOLEAN),
    /**
     * Player spawn radius rule.
     */
    SPAWN_RADIUS("spawnRadius", 5, Type.INT),
    /**
     * TNT explosion permission rule.
     */
    TNT_EXPLODES("tntExplodes", true, Type.BOOLEAN),
    /**
     * TNT drop decay rule.
     */
    TNT_EXPLOSION_DROP_DECAY("tntExplosionDropDecay", false, Type.BOOLEAN),
    /**
     * Entity name tag display rule.
     */
    SHOW_TAGS("showTags", true, Type.BOOLEAN),
    /**
     * Freezing damage permission rule.
     */
    FREEZE_DAMAGE("freezeDamage", true, Type.BOOLEAN),
    /**
     * Respawn block explosion rule.
     */
    RESPAWN_BLOCKS_EXPLODE("respawnBlocksExplode", true, Type.BOOLEAN),
    /**
     * Client border effect display rule.
     */
    SHOW_BORDER_EFFECT("showBorderEffect", true, Type.BOOLEAN),
    /**
     * Client days played display rule.
     */
    SHOW_DAYS_PLAYED("showDaysPlayed", false, Type.BOOLEAN),
    /**
     * Function command execution limit.
     */
    FUNCTION_COMMAND_LIMIT("functionCommandLimit", 10000, Type.INT),
    /**
     * Limited crafting selection rule.
     */
    DO_LIMITED_CRAFTING("dolimitedcrafting", false, Type.BOOLEAN),
    /**
     * Client locator bar display rule.
     */
    LOCATOR_BAR("locatorBar", true, Type.BOOLEAN),
    /**
     * Sleeping player percentage rule.
     */
    PLAYERS_SLEEPING_PERCENTAGE("playersSleepingPercentage", 100, Type.INT);

    /**
     * Canonical spelling used for rule serialization.
     */
    private final String name;
    /**
     * Default value for absent rule entries.
     */
    private final Object defaultValue;
    /**
     * Value category expected by rule consumers.
     */
    private final Type type;

    /**
     * Returns the rule whose canonical name matches without case distinctions.
     * Unknown names and {@code null} produce {@code null}; the lookup does not
     * trim whitespace or accept enum constant names as aliases.
     */
    public static GameRule fromName(String name) {
        return Arrays.stream(values())
                .filter(gameRule -> gameRule.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * {@code Type} identifies the two value categories used by game rule
     * serializers.
     */
    public enum Type {
        /**
         * Integer-valued rule category.
         */
        INT,
        /**
         * Boolean-valued rule category.
         */
        BOOLEAN
    }
}
