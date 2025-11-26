package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class StopCommand extends Command {
    public StopCommand() {
        super("stop", TrKeys.MC_COMMANDS_STOP_DESCRIPTION, Permissions.COMMAND_STOP);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            context.getSender().sendTranslatable(TrKeys.MC_COMMANDS_STOP_START);
            Server.getInstance().shutdown();
            return context.success();
        });
    }
}
