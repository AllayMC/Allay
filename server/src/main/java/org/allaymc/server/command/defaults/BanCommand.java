package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code BanCommand} exposes player-name or UUID bans through the server's
 * player manager. The manager decides whether a ban is accepted and disconnects
 * matching online players after acceptance; the command reports a failed result
 * for an existing ban or a cancelled ban event.
 *
 * @author daoge_cmd
 */
public class BanCommand extends Command {

    /**
     * Creates the {@code /ban} command with its built-in authorization permission.
     */
    public BanCommand() {
        super("ban", TrKeys.MC_COMMANDS_BAN_DESCRIPTION, Permissions.COMMAND_BAN);
    }

    /**
     * Installs a required name-or-UUID argument and its ban handler.
     * The handler forwards the supplied string unchanged to the player manager
     * and uses its boolean result to choose success or failure output.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("nameOrUUID").exec(context -> {
            String nameOrUUID = context.getResult(0);
            if (Server.getInstance().getPlayerManager().ban(nameOrUUID)) {
                context.addOutput(TrKeys.MC_COMMANDS_BAN_SUCCESS, nameOrUUID);
                return context.success();
            } else {
                context.addError("%" + TrKeys.MC_COMMANDS_BAN_FAILED, nameOrUUID);
                return context.fail();
            }
        });
    }
}
