package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.WorldData;

/**
 * Allay Project 2024/3/23
 *
 * @author daoge_cmd
 */
public class TimeCommand extends SimpleCommand {
    public TimeCommand() {
        super("time", TrKeys.M_COMMANDS_TIME_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("set")
                .enums("time", "day", "night", "midnight", "noon", "sunrise", "sunset")
                .exec(context -> {
                    var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
                    long time = 0L;
                    switch ((String) context.getSecondResult()) {
                        case "day" -> {
                            time = WorldData.TIME_DAY;
                        }
                        case "night" -> {
                            time = WorldData.TIME_NIGHT;
                        }
                        case "midnight" -> {
                            time = WorldData.TIME_MIDNIGHT;
                        }
                        case "noon" -> {
                            time = WorldData.TIME_NOON;
                        }
                        case "sunrise" -> {
                            time = WorldData.TIME_SUNRISE;
                        }
                        case "sunset" -> {
                            time = WorldData.TIME_SUNSET;
                        }
                    }
                    world.setTime(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .up()
                .longNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
                    long time = context.getSecondResult();
                    world.setTime(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .root()
                .key("add")
                .longNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
                    long amount = context.getSecondResult();
                    world.addTime(amount);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_ADDED, amount);
                    return context.success();
                });

    }
}
