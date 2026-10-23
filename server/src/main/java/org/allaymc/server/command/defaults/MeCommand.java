package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.OpPermissionCalculator;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * {@code MeCommand} broadcasts an emote containing the sender's name and optional
 * message. Its constructor adds the command's permissions to the shared
 * non-operator permission set, allowing the default operator calculator to grant
 * this command to ordinary players on later checks.
 *
 * @author daoge_cmd
 */
public class MeCommand extends Command {
    /**
     * Creates the emote command and grants its permissions to non-operators.
     * The grant modifies {@link OpPermissionCalculator#NON_OP_PERMISSIONS},
     * affecting subsequent checks made by every calculator that uses that
     * shared set.
     */
    public MeCommand() {
        super("me", TrKeys.MC_COMMANDS_ME_DESCRIPTION, Permissions.COMMAND_ME);
        OpPermissionCalculator.NON_OP_PERMISSIONS.addAll(this.permissions);
    }

    /**
     * Installs emote broadcasting with an optional message argument.
     * The parser supplies an empty string when the message is omitted, and the
     * handler broadcasts the sender's command name with that message through
     * the server message channel.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().msg("message").optional().exec(context -> {
            Object[] args = new Object[]{context.getSender().getCommandSenderName(), context.getResult(0)};
            Server.getInstance().getMessageChannel().broadcastTranslatable(TrKeys.MC_CHAT_TYPE_EMOTE, args);
            return context.success();
        });
    }
}
