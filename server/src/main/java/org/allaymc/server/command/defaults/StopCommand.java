package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class StopCommand extends VanillaCommand {
    public StopCommand() {
        super("stop", TrKeys.M_COMMANDS_STOP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            context.getSender().sendTr(TrKeys.M_COMMANDS_STOP_START);
            Server.getInstance().shutdown();
            return context.success();
        });
    }
}
