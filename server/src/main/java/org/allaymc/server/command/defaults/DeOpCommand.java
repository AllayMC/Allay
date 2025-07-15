package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;

import java.util.Collection;

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
                .playerTarget("player")
                .exec(context -> {
                    Collection<EntityPlayer> players = context.getResult(0);
                    if (players.isEmpty()) {
                        context.addPlayerNotFoundError();
                        return context.fail();
                    }

                    if (players.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    var player = players.stream().findFirst().get();
                    if (!player.isOperator()) {
                        context.addError("%" + TrKeys.M_COMMANDS_DEOP_FAILED, player.getOriginName());
                        return context.fail();
                    }

                    player.setOperator(false);

                    context.addOutput(TrKeys.M_COMMANDS_DEOP_SUCCESS, player.getOriginName());
                    player.sendTr(TrKeys.M_COMMANDS_DEOP_MESSAGE);
                    return context.success();
                });
    }
}