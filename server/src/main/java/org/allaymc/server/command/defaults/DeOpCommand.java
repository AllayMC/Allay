package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;

import java.util.Collection;

/**
 * @author xingchentye
 */
public class DeOpCommand extends VanillaCommand {

    public DeOpCommand() {
        super("deop", TrKeys.MC_COMMANDS_DEOP_DESCRIPTION);
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
            if (!player.isOperator()) {
                context.addError("%" + TrKeys.MC_COMMANDS_DEOP_FAILED, player.getDisplayName());
                return context.fail();
            }

            player.setOperator(false);

            context.addOutput(TrKeys.MC_COMMANDS_DEOP_SUCCESS, player.getDisplayName());
            player.sendTranslatable(TrKeys.MC_COMMANDS_DEOP_MESSAGE);
            return context.success();
        });
    }
}