package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;

/**
 * {@code AlwaysDayCommand} exposes daylight-cycle control for the sender's
 * execution world. Its handler requests the daylight rule change and then a
 * time change independently, so cancellation of either world event does not
 * prevent the other request or the command's completion message.
 *
 * @author xingchentye
 */
public class AlwaysDayCommand extends Command {

    /**
     * Creates the {@code /alwaysday} command with its built-in authorization permission.
     */
    public AlwaysDayCommand() {
        super("alwaysday", TrKeys.MC_COMMANDS_ALWAYS_DAY, Permissions.COMMAND_ALWAYSDAY);
    }

    /**
     * Installs the optional lock argument, which defaults to {@code true}.
     * Execution requests {@link GameRule#DO_DAYLIGHT_CYCLE} as the inverse lock
     * value and sets the time to {@code WorldData.TIME_NOON - 1000}, even when
     * unlocking; event cancellation is not reflected in the command result.
     */
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
