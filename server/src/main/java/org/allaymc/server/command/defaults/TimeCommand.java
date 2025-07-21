package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.world.WorldData;

/**
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
                    var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
                    String strTime = context.getResult(1);
                    int time = switch (strTime) {
                        case "day" -> WorldData.TIME_DAY;
                        case "night" -> WorldData.TIME_NIGHT;
                        case "midnight" -> WorldData.TIME_MIDNIGHT;
                        case "noon" -> WorldData.TIME_NOON;
                        case "sunrise" -> WorldData.TIME_SUNRISE;
                        case "sunset" -> WorldData.TIME_SUNSET;
                        default -> throw new IllegalStateException("Unexpected value: " + strTime);
                    };
                    world.getWorldData().setTimeOfDay(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .up()
                .intNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
                    int time = context.getResult(1);
                    world.getWorldData().setTimeOfDay(time);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_SET, time);
                    return context.success();
                })
                .root()
                .key("add")
                .intNum("amount")
                .exec(context -> {
                    var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
                    int amount = context.getResult(1);
                    world.getWorldData().addTimeOfDay(amount);
                    context.addOutput(TrKeys.M_COMMANDS_TIME_ADDED, amount);
                    return context.success();
                });
    }
}
