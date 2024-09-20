package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class BanIPCommand extends SimpleCommand {

    public BanIPCommand() {
        super("ban-ip", TrKeys.M_COMMANDS_BANIP_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("ip")
                .exec(context -> {
                    String ip = context.getResult(0);
                    if (Server.getInstance().banIP(ip)) {
                        context.addOutput(TrKeys.M_COMMANDS_BANIP_SUCCESS, ip);
                        return context.success();
                    } else {
                        context.addError("Can't ban ip " + ip + ", which may already being banned, or the event is cancelled by plugin.");
                        return context.fail();
                    }
                });
    }
}
