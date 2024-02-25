package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/24
 *
 * @author daoge_cmd
 */
public class OpCommand extends SimpleCommand {

    public OpCommand() {
        super("op", TrKeys.M_COMMANDS_OP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("playerName")
                .exec(context -> {
                    // TODO: Support offline player
                    String playerName = context.getFirstResult();
                    var player = Server.getInstance().findOnlinePlayerByName(playerName);
                    if (player == null) {
                        context.addError(TrKeys.M_COMMANDS_GENERIC_PLAYER_NOTFOUND);
                        return context.failed();
                    }
                    if (player.isOp()) {
                        context.addError(TrKeys.M_COMMANDS_OP_FAILED, playerName);
                        return context.failed();
                    }
                    player.setOp(true);
                    context.addOutput(TrKeys.M_COMMANDS_OP_SUCCESS, playerName);
                    player.sendTr(TrKeys.M_COMMANDS_OP_MESSAGE);
                    return context.success();
                });
    }
}
