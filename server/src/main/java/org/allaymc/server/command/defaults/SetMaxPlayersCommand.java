package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.allaymc.server.AllayServer;

/**
 * {@code SetMaxPlayersCommand} updates the configured and live player capacity.
 * The requested capacity is clamped to the current connected-player count,
 * preventing this command from setting a limit below existing occupancy; the
 * resulting value is supplied to both settings and the player manager.
 *
 * @author daoge_cmd
 */
public class SetMaxPlayersCommand extends Command {

    /**
     * Creates the {@code /setmaxplayers} command with its built-in authorization permission.
     */
    public SetMaxPlayersCommand() {
        super("setmaxplayers", TrKeys.MC_COMMANDS_SETMAXPLAYERS_DESCRIPTION, Permissions.COMMAND_SETMAXPLAYERS);
    }

    /**
     * Installs capacity changes clamped to the current connected-player count.
     * The handler updates the settings value before the player manager and
     * reports the clamped result; it does not explicitly save the settings file.
     */
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
