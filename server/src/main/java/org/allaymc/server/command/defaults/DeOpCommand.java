package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author xingchentye
 */
public class DeOpCommand extends SimpleCommand {

    public DeOpCommand() {
        super("deop", TrKeys.M_COMMANDS_DEOP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("playerName")
                .exec(context -> {
                    String playerName = context.getResult(0);
                    var player = Server.getInstance().findOnlinePlayerByName(playerName);
                    if (player == null) {
                        context.addError("%" + TrKeys.M_COMMANDS_GENERIC_PLAYER_NOTFOUND);
                        return context.fail();
                    }

                    if (!player.isOp()) {
                        context.addError("%" + TrKeys.M_COMMANDS_DEOP_FAILED, playerName);
                        return context.fail();
                    }

                    player.setOp(false);

                    context.addOutput(TrKeys.M_COMMANDS_DEOP_SUCCESS, playerName);
                    player.sendTr(TrKeys.M_COMMANDS_DEOP_MESSAGE);
                    return context.success();
                });
    }
}