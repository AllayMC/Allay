package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;

/**
 * @author daoge_cmd
 */
public class SetMaxPlayersCommand extends Command {

    public SetMaxPlayersCommand() {
        super("setmaxplayers", TrKeys.MC_COMMANDS_SETMAXPLAYERS_DESCRIPTION, Permissions.COMMAND_SETMAXPLAYERS);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().intNum("maxPlayers").exec(context -> {
            var maxPlayers = Math.max(Server.getInstance().getPlayerManager().getPlayerCount(), context.getResult(0));

            AllayServer.getSettings().genericSettings().maxPlayerCount(maxPlayers);
            Server.getInstance().getPlayerManager().setMaxPlayerCount(maxPlayers);
            context.addOutput(TrKeys.MC_COMMANDS_SETMAXPLAYERS_SUCCESS, maxPlayers);
            return context.success();
        });
    }
}