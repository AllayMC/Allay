package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;

/**
 * @author daoge_cmd
 */
public class SayCommand extends Command {

    public SayCommand() {
        super("say", TrKeys.MC_COMMANDS_SAY_DESCRIPTION, "allay.command.say");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .msg("message")
                .exec(context -> {
                    String message = context.getResult(0);
                    Server.getInstance().getMessageChannel().broadcastTranslatable(
                            TrKeys.MC_CHAT_TYPE_ANNOUNCEMENT,
                            context.getSender().getCommandSenderName(),
                            message
                    );

                    return context.success();
                });
    }
}
