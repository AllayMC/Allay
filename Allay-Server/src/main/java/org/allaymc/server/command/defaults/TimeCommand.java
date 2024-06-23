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
                    String strTime = context.getResult(1);
                    long time = switch (strTime) {
                        case "day" -> WorldData.TIME_DAY;
                        case "night" -> WorldData.TIME_NIGHT;
                        case "midnight" -> WorldData.TIME_MIDNIGHT;
                        case "noon" -> WorldData.TIME_NOON;
                        case "sunrise" -> WorldData.TIME_SUNRISE;
                        case "sunset" -> WorldData.TIME_SUNSET;
                        default -> throw new IllegalStateException("Unexpected value: " + strTime);
                    };
                    world.setTime(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .up()
                .longNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
                    long time = context.getResult(1);
                    world.setTime(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .root()
                .key("add")
                .longNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCmdExecuteLocation().dimension().getWorld();
                    long amount = context.getResult(1);
                    world.addTime(amount);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_ADDED, amount);
                    return context.success();
                });
    }
}
