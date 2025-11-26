package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class BanIPCommand extends Command {

    public BanIPCommand() {
        super("ban-ip", TrKeys.MC_COMMANDS_BANIP_DESCRIPTION, Permissions.COMMAND_BAN_IP);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("ip").exec(context -> {
            String ip = context.getResult(0);
            if (Server.getInstance().getPlayerManager().banIP(ip)) {
                context.addOutput(TrKeys.MC_COMMANDS_BANIP_SUCCESS, ip);
                return context.success();
            } else {
                context.addError("Can't ban ip " + ip + ", which may already being banned, or the event is cancelled by plugin.");
                return context.fail();
            }
        });
    }
}
