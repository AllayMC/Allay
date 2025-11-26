package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;

/**
 * @author xingchentye
 */
public class AlwaysDayCommand extends Command {

    public AlwaysDayCommand() {
        super("alwaysday", TrKeys.MC_COMMANDS_ALWAYS_DAY, Permissions.COMMAND_ALWAYSDAY);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().bool("lock", true).optional().exec(context -> {
            var world = context.getSender().getCommandExecuteLocation().dimension().getWorld();
            boolean lock = context.getResult(0);

            world.getWorldData().setGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE, !lock);
            world.getWorldData().setTimeOfDay(WorldData.TIME_NOON - 1000);

            if (lock) {
                context.addOutput(TrKeys.MC_COMMANDS_ALWAYS_DAY_LOCKED);
            } else {
                context.addOutput(TrKeys.MC_COMMANDS_ALWAYS_DAY_UNLOCKED);
            }
            return context.success();
        });
    }
}
