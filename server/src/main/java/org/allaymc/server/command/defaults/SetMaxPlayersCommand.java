package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class SetMaxPlayersCommand extends VanillaCommand {

    public SetMaxPlayersCommand() {
        super("setmaxplayers", TrKeys.MC_COMMANDS_SETMAXPLAYERS_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().intNum("maxPlayers").exec(context -> {
            var maxPlayers = Math.max(Server.getInstance().getPlayerService().getPlayerCount(), context.getResult(0));

            Server.SETTINGS.genericSettings().maxPlayerCount(maxPlayers);
            Server.getInstance().getPlayerService().setMaxPlayerCount(maxPlayers);
            context.addOutput(TrKeys.MC_COMMANDS_SETMAXPLAYERS_SUCCESS, maxPlayers);
            return context.success();
        });
    }
}