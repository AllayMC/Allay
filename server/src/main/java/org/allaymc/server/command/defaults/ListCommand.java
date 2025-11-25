package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;

import java.util.StringJoiner;

/**
 * @author daoge_cmd
 */
public class ListCommand extends VanillaCommand {
    public ListCommand() {
        super("list", TrKeys.MC_COMMANDS_LIST_DESCRIPTION);
    }

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
