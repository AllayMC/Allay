package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class MeCommand extends Command {
    public MeCommand() {
        super("me", TrKeys.MC_COMMANDS_ME_DESCRIPTION, Permissions.COMMAND_ME);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().msg("message").optional().exec(context -> {
            Object[] args = new Object[]{context.getSender().getCommandSenderName(), context.getResult(0)};
            Server.getInstance().getMessageChannel().broadcastTranslatable(TrKeys.MC_CHAT_TYPE_EMOTE, args);
            return context.success();
        });
    }
}
