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
 * @author daoge_cmd
 */
public class TimeCommand extends Command {
    public TimeCommand() {
        super("time", TrKeys.MC_COMMANDS_TIME_DESCRIPTION, Permissions.COMMAND_TIME);
    }

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

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private enum TimePreset {
        DAY(WorldData.TIME_DAY, "day"),
        NIGHT(WorldData.TIME_NIGHT, "night"),
        MIDNIGHT(WorldData.TIME_MIDNIGHT, "midnight"),
        NOON(WorldData.TIME_NOON, "noon"),
        SUNRISE(WorldData.TIME_SUNRISE, "sunrise"),
        SUNSET(WorldData.TIME_SUNSET, "sunset");

        private final int time;
        private final String name;
    }
}
