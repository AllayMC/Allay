package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class UnbanIPCommand extends VanillaCommand {

    public UnbanIPCommand() {
        super("unban-ip", "Unban an ip");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("ip").exec(context -> {
            String ip = context.getResult(0);
            if (Server.getInstance().getPlayerService().unbanIP(ip)) {
                context.addOutput(TrKeys.M_COMMANDS_UNBANIP_SUCCESS, ip);
                return context.success();
            } else {
                context.addError("IP " + ip + " is not banned!");
                return context.fail();
            }
        });
    }
}
