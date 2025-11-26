package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class UnbanCommand extends Command {

    public UnbanCommand() {
        super("unban", "Unban a player", Permissions.COMMAND_UNBAN);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("nameOrUUID").exec(context -> {
            String nameOrUUID = context.getResult(0);
            if (Server.getInstance().getPlayerManager().unban(nameOrUUID)) {
                context.addOutput(TrKeys.MC_COMMANDS_UNBAN_SUCCESS, nameOrUUID);
                return context.success();
            } else {
                context.addError("%" + TrKeys.MC_COMMANDS_UNBAN_FAILED, nameOrUUID);
                return context.fail();
            }
        });
    }
}
