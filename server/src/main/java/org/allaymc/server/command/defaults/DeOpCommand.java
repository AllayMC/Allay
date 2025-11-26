package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

import java.util.Collection;

/**
 * @author xingchentye
 */
public class DeOpCommand extends Command {

    public DeOpCommand() {
        super("deop", TrKeys.MC_COMMANDS_DEOP_DESCRIPTION, Permissions.COMMAND_DEOP);
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
            if (!player.isActualPlayer()) {
                context.addPlayerNotFoundError();
                return context.fail();
            }

            var manager = Server.getInstance().getPlayerManager();
            if (!manager.isOperator(player.getController())) {
                context.addError("%" + TrKeys.MC_COMMANDS_DEOP_FAILED, player.getDisplayName());
                return context.fail();
            }

            manager.setOperator(player.getController().getOriginName(), false);

            context.addOutput(TrKeys.MC_COMMANDS_DEOP_SUCCESS, player.getDisplayName());
            player.sendTranslatable(TrKeys.MC_COMMANDS_DEOP_MESSAGE);
            return context.success();
        });
    }
}