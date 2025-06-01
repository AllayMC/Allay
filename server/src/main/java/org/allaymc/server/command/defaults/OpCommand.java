package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
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
                    String playerName = context.getResult(0);
                    var player = Server.getInstance().getPlayerService().getOnlinePlayerByName(playerName);
                    if (player == null) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_PLAYER_NOTFOUND);
                        return context.fail();
                    }

                    if (player.isOperator()) {
                        context.addError("%" + TrKeys.M_COMMANDS_OP_FAILED, playerName);
                        return context.fail();
                    }

                    player.setOperator(true);

                    context.addOutput(TrKeys.M_COMMANDS_OP_SUCCESS, playerName);
                    player.sendTr(TrKeys.M_COMMANDS_OP_MESSAGE);
                    return context.success();
                });
    }
}
