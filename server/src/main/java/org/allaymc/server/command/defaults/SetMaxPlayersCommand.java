package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class SetMaxPlayersCommand extends SimpleCommand {

    public SetMaxPlayersCommand() {
        super("setmaxplayers", TrKeys.M_COMMANDS_SETMAXPLAYERS_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .intNum("maxPlayers")
                .exec(context -> {
                    int maxPlayers = context.getResult(0);
                    maxPlayers = Math.max(Server.getInstance().getOnlinePlayerCount(), maxPlayers);

                    Server.SETTINGS.genericSettings().maxPlayerCount(maxPlayers);
                    Server.getInstance().getNetworkInterface().setMaxPlayerCount(maxPlayers);
                    context.addOutput(TrKeys.M_COMMANDS_SETMAXPLAYERS_SUCCESS, maxPlayers);

                    return context.success();
                });
    }
}