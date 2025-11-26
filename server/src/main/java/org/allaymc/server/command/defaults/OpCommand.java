package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class OpCommand extends Command {

    public OpCommand() {
        super("op", TrKeys.MC_COMMANDS_OP_DESCRIPTION, Permissions.COMMAND_OP);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().wildcardTarget("player").exec(context -> {
            String player = context.getResult(0);

            var manager = Server.getInstance().getPlayerManager();
            if (manager.isOperator(player)) {
                context.addError("%" + TrKeys.MC_COMMANDS_OP_FAILED, player);
                return context.fail();
            }

            manager.setOperator(player, true);
            context.addOutput(TrKeys.MC_COMMANDS_OP_SUCCESS, player);
            manager.getPlayers().values().stream()
                    .filter(p -> p.getLoginData().getUuid().toString().equals(player) || p.getOriginName().equals(player))
                    .forEach(p -> p.sendTranslatable(TrKeys.MC_COMMANDS_OP_MESSAGE));
            return context.success();
        });
    }
}
