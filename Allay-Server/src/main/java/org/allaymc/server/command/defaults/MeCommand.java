package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public class MeCommand extends SimpleCommand {

    public MeCommand() {
        super("me", TrKeys.M_COMMANDS_ME_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .str("message")
                .exec(context -> {
                    Server.getInstance().broadcastTr(TrKeys.M_CHAT_TYPE_EMOTE, context.getSender().getName(), context.getResult(0));
                    return context.success();
                });
    }
}
