package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code StopCommand} requests shutdown of the running server through its server
 * abstraction. The handler first sends a shutdown-start message directly to the
 * command sender, then invokes shutdown and returns a successful result when
 * that call returns.
 *
 * @author daoge_cmd
 */
public class StopCommand extends Command {
    /**
     * Creates the {@code /stop} command with its built-in authorization permission.
     */
    public StopCommand() {
        super("stop", TrKeys.MC_COMMANDS_STOP_DESCRIPTION, Permissions.COMMAND_STOP);
    }

    /**
     * Installs the argument-free server shutdown request on the command root.
     * The sender receives the start message before shutdown begins, and the
     * handler produces success only after the shutdown call returns.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            context.getSender().sendTranslatable(TrKeys.MC_COMMANDS_STOP_START);
            Server.getInstance().shutdown();
            return context.success();
        });
    }
}
