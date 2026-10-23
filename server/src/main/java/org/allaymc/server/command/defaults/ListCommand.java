package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

import java.util.StringJoiner;

/**
 * {@code ListCommand} displays the current connected-player count, configured
 * capacity, and controlled entities' display names. Its handler reads the player
 * manager's current collection and sends the two resulting messages directly
 * to the command sender without taking command arguments.
 *
 * @author daoge_cmd
 */
public class ListCommand extends Command {
    /**
     * Creates the {@code /list} command with its built-in authorization permission.
     */
    public ListCommand() {
        super("list", TrKeys.MC_COMMANDS_LIST_DESCRIPTION, Permissions.COMMAND_LIST);
    }

    /**
     * Installs the argument-free connected-player listing handler on the root.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            var playerManager = Server.getInstance().getPlayerManager();
            var players = playerManager.getPlayers().values();

            var joiner = new StringJoiner(", ");
            players.forEach(player -> joiner.add(player.getControlledEntity().getDisplayName()));

            context.getSender().sendTranslatable(TrKeys.MC_COMMANDS_PLAYERS_LIST, players.size(), playerManager.getMaxPlayerCount());
            context.getSender().sendMessage(joiner.toString());
            return context.success();
        });
    }
}
