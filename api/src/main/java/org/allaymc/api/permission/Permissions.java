package org.allaymc.api.permission;

/**
 * {@code Permissions} names the built-in authorization checks used by Allay's
 * abilities, blocks, and commands. These string constants identify permission
 * queries; a subject's calculator supplies their decisions, so declaring a
 * permission here does not grant it to any subject.
 *
 * @author daoge_cmd
 */
public interface Permissions {

    /* The ability permissions control chat submission and the operator controls
       exposed to players by their client ability updates. */

    /**
     * The permission to chat.
     */
    String ABILITY_CHAT = "ability.chat";
    /**
     * The permission to have operator command quick bar in chat screen.
     */
    String ABILITY_OPERATOR_COMMAND_QUICK_BAR = "ability.operator_command_quick_bar";

    // TODO: Define input permission names when input restrictions acquire
    // server-side permission checks.

    /* The block permission identifies the authorization check used for command
       block placement, editing, and command-block output visibility. */

    /**
     * The permission to use command blocks (modify and view output).
     */
    String BLOCK_USE_COMMAND_BLOCK = "allay.block.use_command_block";

    /* The command permissions identify command-tree access checks.
       COMMAND_VIEW_OTHER_OUTPUTS controls another sender's output, and
       COMMAND_DEFAULTGAMEMODE controls client default-game-mode updates. */

    /**
     * The permission to view other's command outputs.
     */
    String COMMAND_VIEW_OTHER_OUTPUTS = "allay.command.view_other_outputs";
    /**
     * The permission to use {@code /alwaysday} command.
     */
    String COMMAND_ALWAYSDAY = "allay.command.alwaysday";
    /**
     * The permission to use {@code /ban} command.
     */
    String COMMAND_BAN = "allay.command.ban";
    /**
     * The permission to use {@code /ban-ip} command.
     */
    String COMMAND_BAN_IP = "allay.command.ban-ip";
    /**
     * The permission to use {@code /camerashake} command.
     */
    String COMMAND_CAMERASHAKE = "allay.command.camerashake";
    /**
     * The permission to use {@code /clear} command.
     */
    String COMMAND_CLEAR = "allay.command.clear";
    /**
     * The permission to use {@code /deop} command.
     */
    String COMMAND_DEOP = "allay.command.deop";
    /**
     * The permission to request default-game-mode changes from the client.
     */
    String COMMAND_DEFAULTGAMEMODE = "allay.command.defaultgamemode";
    /**
     * The permission to use {@code /difficulty} command.
     */
    String COMMAND_DIFFICULTY = "allay.command.difficulty";
    /**
     * The permission to use {@code /effect} command.
     */
    String COMMAND_EFFECT = "allay.command.effect";
    /**
     * The permission to use {@code /enchant} command.
     */
    String COMMAND_ENCHANT = "allay.command.enchant";
    /**
     * The permission to use {@code /execute} command.
     */
    String COMMAND_EXECUTE = "allay.command.execute";
    /**
     * The permission to use {@code /fill} command.
     */
    String COMMAND_FILL = "allay.command.fill";
    /**
     * The permission to use {@code /fog} command.
     */
    String COMMAND_FOG = "allay.command.fog";
    /**
     * The permission to use {@code /gamemode} command.
     */
    String COMMAND_GAMEMODE = "allay.command.gamemode";
    /**
     * The permission to use {@code /gamerule} command.
     */
    String COMMAND_GAMERULE = "allay.command.gamerule";
    /**
     * The permission to use {@code /gc} command.
     */
    String COMMAND_GC = "allay.command.gc";
    /**
     * The permission to use {@code /give} command.
     */
    String COMMAND_GIVE = "allay.command.give";
    /**
     * The permission to use {@code /help} command.
     */
    String COMMAND_HELP = "allay.command.help";
    /**
     * The permission to use {@code /hud} command.
     */
    String COMMAND_HUD = "allay.command.hud";
    /**
     * The permission to use {@code /kick} command.
     */
    String COMMAND_KICK = "allay.command.kick";
    /**
     * The permission to use {@code /kill} command.
     */
    String COMMAND_KILL = "allay.command.kill";
    /**
     * The permission to use {@code /list} command.
     */
    String COMMAND_LIST = "allay.command.list";
    /**
     * The permission to use {@code /me} command.
     */
    String COMMAND_ME = "allay.command.me";
    /**
     * The permission to use {@code /op} command.
     */
    String COMMAND_OP = "allay.command.op";
    /**
     * The permission to use {@code /particle} command.
     */
    String COMMAND_PARTICLE = "allay.command.particle";
    /**
     * The permission to use {@code /permission} command.
     */
    String COMMAND_PERMISSION = "allay.command.permission";
    /**
     * The permission to use {@code /playanimation} command.
     */
    String COMMAND_PLAYANIMATION = "allay.command.playanimation";
    /**
     * The permission to use {@code /playsound} command.
     */
    String COMMAND_PLAYSOUND = "allay.command.playsound";
    /**
     * The permission to use {@code /plugin} command.
     */
    String COMMAND_PLUGIN = "allay.command.plugin";
    /**
     * The permission to use {@code /scoreboard} command.
     */
    String COMMAND_SCOREBOARD = "allay.command.scoreboard";
    /**
     * The permission to use {@code /setblock} command.
     */
    String COMMAND_SETBLOCK = "allay.command.setblock";
    /**
     * The permission to use {@code /setmaxplayers} command.
     */
    String COMMAND_SETMAXPLAYERS = "allay.command.setmaxplayers";
    /**
     * The permission to use {@code /setworldspawn} command.
     */
    String COMMAND_SETWORLDSPAWN = "allay.command.setworldspawn";
    /**
     * The permission to use {@code /spawnpoint} command.
     */
    String COMMAND_SPAWNPOINT = "allay.command.spawnpoint";
    /**
     * The permission to use {@code /status} command.
     */
    String COMMAND_STATUS = "allay.command.status";
    /**
     * The permission to use {@code /stop} command.
     */
    String COMMAND_STOP = "allay.command.stop";
    /**
     * The permission to use {@code /stopsound} command.
     */
    String COMMAND_STOPSOUND = "allay.command.stopsound";
    /**
     * The permission to use {@code /structure} command.
     */
    String COMMAND_STRUCTURE = "allay.command.structure";
    /**
     * The permission to use {@code /summon} command.
     */
    String COMMAND_SUMMON = "allay.command.summon";
    /**
     * The permission to use {@code /tag} command.
     */
    String COMMAND_TAG = "allay.command.tag";
    /**
     * The permission to use {@code /teleport} command.
     */
    String COMMAND_TELEPORT = "allay.command.teleport";
    /**
     * The permission to use {@code /testfor} command.
     */
    String COMMAND_TESTFOR = "allay.command.testfor";
    /**
     * The permission to use {@code /time} command.
     */
    String COMMAND_TIME = "allay.command.time";
    /**
     * The permission to use {@code /title} command.
     */
    String COMMAND_TITLE = "allay.command.title";
    /**
     * The permission to use {@code /tps} command.
     */
    String COMMAND_TPS = "allay.command.tps";
    /**
     * The permission to use {@code /unban} command.
     */
    String COMMAND_UNBAN = "allay.command.unban";
    /**
     * The permission to use {@code /unban-ip} command.
     */
    String COMMAND_UNBAN_IP = "allay.command.unban-ip";
    /**
     * The permission to use {@code /version} command.
     */
    String COMMAND_VERSION = "allay.command.version";
    /**
     * The permission to use {@code /weather} command.
     */
    String COMMAND_WEATHER = "allay.command.weather";
    /**
     * The permission to use {@code /whitelist} command.
     */
    String COMMAND_WHITELIST = "allay.command.whitelist";
    /**
     * The permission to use {@code /world} command.
     */
    String COMMAND_WORLD = "allay.command.world";
    /**
     * The permission to use {@code /xp} command.
     */
    String COMMAND_XP = "allay.command.xp";
}
