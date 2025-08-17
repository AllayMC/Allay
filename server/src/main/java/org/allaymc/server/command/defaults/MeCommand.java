package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;

/**
 * @author daoge_cmd
 */
public class MeCommand extends VanillaCommand {
    public MeCommand() {
        super("me", TrKeys.M_COMMANDS_ME_DESCRIPTION);
        flags.add(CommandData.Flag.MESSAGE_TYPE);
        getPermissions().forEach(PermissionGroups.MEMBER::addPermission);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().msg("message").optional().exec(context -> {
            Server.getInstance().broadcastTr(
                    TrKeys.M_CHAT_TYPE_EMOTE,
                    context.getSender().getCommandSenderName(),
                    context.getResult(0)
            );
            return context.success();
        });
    }
}
