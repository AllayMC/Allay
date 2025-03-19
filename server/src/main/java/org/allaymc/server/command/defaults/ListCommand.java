package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

import java.util.StringJoiner;

/**
 * @author daoge_cmd
 */
public class ListCommand extends SimpleCommand {
    public ListCommand() {
        super("list", TrKeys.M_COMMANDS_LIST_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            var playerService = Server.getInstance().getPlayerService();
            var players = playerService.getPlayers().values();

            var joiner = new StringJoiner(", ");
            players.forEach(player -> joiner.add(player.getDisplayName()));

            context.getSender().sendTr(TrKeys.M_COMMANDS_PLAYERS_LIST, players.size(), playerService.getMaxPlayerCount());
            context.getSender().sendText(joiner.toString());
            return context.success();
        });
    }
}
