package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

public class StopCommand extends SimpleCommand {

    public StopCommand() {
        super("stop", TrKeys.M_COMMANDS_STOP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            context.getSender().sendText("Shutting down the server!");
            Server.getInstance().shutdown();
            return context.success();
        });
    }
}
