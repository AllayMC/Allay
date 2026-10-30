package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code UnbanCommand} removes player-name or UUID bans through the server's
 * player manager. Its handler preserves the supplied identifier and reports the
 * manager's result, so a missing ban or cancelled unban event produces a failed
 * command instead of successful removal feedback.
 *
 * @author daoge_cmd
 */
public class UnbanCommand extends Command {

    /**
     * Creates the {@code /unban} command with its built-in authorization permission.
     */
    public UnbanCommand() {
        super("unban", "Unban a player", Permissions.COMMAND_UNBAN);
    }

    /**
     * Installs the required name-or-UUID argument and its unban handler.
     * The player manager receives the string unchanged, checks existing
     * membership and event acceptance, and supplies the boolean result used for
     * command feedback.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().str("nameOrUUID").exec(context -> {
            String nameOrUUID = context.getResult(0);
            if (Server.getInstance().getPlayerManager().unban(nameOrUUID)) {
                context.addOutput(TrKeys.MC_COMMANDS_UNBAN_SUCCESS, nameOrUUID);
                return context.success();
            } else {
                context.addError("%" + TrKeys.MC_COMMANDS_UNBAN_FAILED, nameOrUUID);
                return context.fail();
            }
        });
    }
}
