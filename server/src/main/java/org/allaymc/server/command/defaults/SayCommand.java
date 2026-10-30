package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.server.Server;

/**
 * {@code SayCommand} broadcasts an announcement containing the command sender's
 * name and supplied message. Its handler sends the announcement through the
 * server message channel, so the command's translated announcement format is
 * shared by recipients rather than assembled separately here.
 *
 * @author daoge_cmd
 */
public class SayCommand extends Command {

    /**
     * Creates the announcement command using {@code "allay.command.say"} for authorization.
     */
    public SayCommand() {
        super("say", TrKeys.MC_COMMANDS_SAY_DESCRIPTION, "allay.command.say");
    }

    /**
     * Installs the required message argument and translated announcement handler.
     */
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
