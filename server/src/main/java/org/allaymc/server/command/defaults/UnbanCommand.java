package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class UnbanCommand extends VanillaCommand {

    public UnbanCommand() {
        super("unban", "Unban a player");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("nameOrUUID").exec(context -> {
            String nameOrUUID = context.getResult(0);
            if (Server.getInstance().getPlayerService().unban(nameOrUUID)) {
                context.addOutput(TrKeys.M_COMMANDS_UNBAN_SUCCESS, nameOrUUID);
                return context.success();
            } else {
                context.addError("%" + TrKeys.M_COMMANDS_UNBAN_FAILED, nameOrUUID);
                return context.fail();
            }
        });
    }
}
