package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code UnbanIPCommand} removes address bans through the server's player
 * manager. The supplied address is passed unchanged, and the manager's boolean
 * result determines feedback. An absent ban and a cancelled unban event both
 * produce the handler's same failure message.
 *
 * @author daoge_cmd
 */
public class UnbanIPCommand extends Command {

    /**
     * Creates the {@code /unban-ip} command with its built-in authorization permission.
     */
    public UnbanIPCommand() {
        super("unban-ip", "Unban an ip", Permissions.COMMAND_UNBAN_IP);
    }

    /**
     * Installs the required address string and its unban handler.
     * The player manager decides whether removal is accepted; both missing
     * membership and cancelled events return failure and use the same
     * address-not-banned feedback.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("ip").exec(context -> {
            String ip = context.getResult(0);
            if (Server.getInstance().getPlayerManager().unbanIP(ip)) {
                context.addOutput(TrKeys.MC_COMMANDS_UNBANIP_SUCCESS, ip);
                return context.success();
            } else {
                context.addError("IP " + ip + " is not banned!");
                return context.fail();
            }
        });
    }
}
