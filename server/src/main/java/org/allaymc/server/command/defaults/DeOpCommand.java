package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code DeOpCommand} removes operator membership for a supplied player name or
 * UUID. Its handler rejects identifiers that are not currently operators, asks
 * the manager to update membership, and sends a separate notification to every
 * matching online player after that update.
 *
 * @author xingchentye
 */
public class DeOpCommand extends Command {

    /**
     * Creates the {@code /deop} command with its built-in authorization permission.
     */
    public DeOpCommand() {
        super("deop", TrKeys.MC_COMMANDS_DEOP_DESCRIPTION, Permissions.COMMAND_DEOP);
    }

    /**
     * Installs the required operator identifier and revocation handler.
     * Existing operator membership is checked before mutation; accepted changes
     * update the manager before command feedback and matching online-player
     * notifications are emitted.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().wildcardTarget("player").exec(context -> {
            String player = context.getResult(0);

            var manager = Server.getInstance().getPlayerManager();
            if (!manager.isOperator(player)) {
                context.addError("%" + TrKeys.MC_COMMANDS_DEOP_FAILED, player);
                return context.fail();
            }

            manager.setOperator(player, false);
            context.addOutput(TrKeys.MC_COMMANDS_DEOP_SUCCESS, player);
            manager.getPlayers().values().stream()
                    .filter(p -> p.getLoginData().getUuid().toString().equals(player) || p.getOriginName().equals(player))
                    .forEach(p -> p.sendTranslatable(TrKeys.MC_COMMANDS_DEOP_MESSAGE));
            return context.success();
        });
    }
}
