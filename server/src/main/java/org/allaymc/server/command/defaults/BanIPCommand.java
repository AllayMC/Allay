package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code BanIPCommand} exposes address bans through the server's player manager.
 * The manager checks its ban set and fires the ban event before adding the
 * supplied address and disconnecting matching online players; the command uses
 * that operation's result for its feedback.
 *
 * @author daoge_cmd
 */
public class BanIPCommand extends Command {

    /**
     * Creates the {@code /ban-ip} command with its built-in authorization permission.
     */
    public BanIPCommand() {
        super("ban-ip", TrKeys.MC_COMMANDS_BANIP_DESCRIPTION, Permissions.COMMAND_BAN_IP);
    }

    /**
     * Installs a required address string and its ban handler.
     * The handler passes the string unchanged to the player manager; an existing
     * entry or cancelled ban event produces a failed command result.
     */
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
