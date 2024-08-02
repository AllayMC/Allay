package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class ListCommand extends SimpleCommand {
    public ListCommand() {
        super("list", TrKeys.M_COMMANDS_LIST_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec(context -> {
                    StringBuilder str = new StringBuilder();
                    int c = 0;
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        str.append(player.getDisplayName()).append(", ");
                        c++;
                    }
                    context.getSender().sendTr(TrKeys.M_COMMANDS_PLAYERS_LIST, c, Server.getInstance().getNetworkServer().getMaxPlayerCount());
                    context.getSender().sendText(str.toString());
                    return context.success();
                });
    }
}
