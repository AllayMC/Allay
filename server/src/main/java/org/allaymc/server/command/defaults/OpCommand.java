package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;

import java.util.Collection;

/**
 * @author daoge_cmd
 */
public class OpCommand extends VanillaCommand {

    public OpCommand() {
        super("op", TrKeys.MC_COMMANDS_OP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().playerTarget("player").exec(context -> {
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
            if (player.isOperator()) {
                context.addError("%" + TrKeys.MC_COMMANDS_OP_FAILED, player.getOriginName());
                return context.fail();
            }

            player.setOperator(true);

            context.addOutput(TrKeys.MC_COMMANDS_OP_SUCCESS, player.getOriginName());
            player.sendTranslatable(TrKeys.MC_COMMANDS_OP_MESSAGE);
            return context.success();
        });
    }
}
