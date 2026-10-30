package org.allaymc.server.command.defaults;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.WorldData;

/**
 * {@code TimeCommand} requests time-of-day changes in the sender's execution
 * world using named presets, integer values, or relative additions. World data
 * owns time wrapping and change events; command feedback reports the requested
 * value or addition even when an event changes or cancels it.
 *
 * @author daoge_cmd
 */
public class TimeCommand extends Command {
    /**
     * Creates the {@code /time} command with its built-in authorization permission.
     */
    public TimeCommand() {
        super("time", TrKeys.MC_COMMANDS_TIME_DESCRIPTION, Permissions.COMMAND_TIME);
    }

    /**
     * Installs named and numeric time setting plus relative time addition.
     * Values use world ticks, and world data applies its time-of-day wrapping
     * and event handling. The handlers report success without inspecting the
     * resulting world time.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().key("set").enumClass("time", TimePreset.class).exec(context -> {
            var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
            TimePreset timePreset = context.getResult(1);
            world.getWorldData().setTimeOfDay(timePreset.getTime());
            context.addOutput(TrKeys.MC_COMMANDS_TIME_SET, timePreset.getTime());
            return context.success();
        }).up().intNum("amount").exec(context -> {
            var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
            int time = context.getResult(1);
            world.getWorldData().setTimeOfDay(time);
            context.addOutput(TrKeys.MC_COMMANDS_TIME_SET, time);
            return context.success();
        }).root().key("add").intNum("amount").exec(context -> {
            var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
            int amount = context.getResult(1);
            world.getWorldData().addTimeOfDay(amount);
            context.addOutput(TrKeys.MC_COMMANDS_TIME_ADDED, amount);
            return context.success();
        });
    }

    /**
     * {@code TimePreset} associates named command alternatives with predefined
     * world times. Each constant supplies the tick value used by the named
     * setting branch and a stored display name.
     */
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private enum TimePreset {
        /**
         * Daytime world time preset.
         */
        DAY(WorldData.TIME_DAY, "day"),
        /**
         * Nighttime world time preset.
         */
        NIGHT(WorldData.TIME_NIGHT, "night"),
        /**
         * Midnight world time preset.
         */
        MIDNIGHT(WorldData.TIME_MIDNIGHT, "midnight"),
        /**
         * Noon world time preset.
         */
        NOON(WorldData.TIME_NOON, "noon"),
        /**
         * Sunrise world time preset.
         */
        SUNRISE(WorldData.TIME_SUNRISE, "sunrise"),
        /**
         * Sunset world time preset.
         */
        SUNSET(WorldData.TIME_SUNSET, "sunset");

        private final int time; // world time in ticks
        private final String name; // stored preset display name
    }
}
