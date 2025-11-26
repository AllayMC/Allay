package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class UnbanIPCommand extends Command {

    public UnbanIPCommand() {
        super("unban-ip", "Unban an ip", Permissions.COMMAND_UNBAN_IP);
    }

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
