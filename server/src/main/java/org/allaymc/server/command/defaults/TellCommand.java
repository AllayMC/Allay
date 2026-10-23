package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;

import java.util.List;

/**
 * {@code TellCommand} sends paired outgoing and incoming private-message feedback
 * for each selected player. A player sender targeting itself receives an error
 * for that target while processing continues; a nonempty selection still
 * returns success even when every target is skipped this way.
 *
 * @author daoge_cmd
 */
public class TellCommand extends Command {

    /**
     * Creates the private-message command and its {@code /msg} and {@code /w} aliases.
     */
    public TellCommand() {
        super("tell", TrKeys.MC_COMMANDS_TELL_DESCRIPTION, "allay.command.tell");
        this.aliases.add("msg");
        this.aliases.add("w");
    }

    /**
     * Installs private messaging with required targets and message text.
     * Empty selections fail before sending, self-targets add errors without
     * aborting other deliveries, and every other target receives its incoming
     * message after the sender's outgoing confirmation.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .msg("message")
                .exec(context -> {
                    List<EntityPlayer> players = context.getResult(0);
                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }

                    String message = context.getResult(1);
                    for (var player : players) {
                        var sender = context.getSender();
                        if (sender.isPlayer() && sender.asPlayer() == player) {
                            context.addError("%" + TrKeys.MC_COMMANDS_MESSAGE_SAMETARGET);
                            continue;
                        }

                        sender.sendTranslatable(TrKeys.MC_COMMANDS_MESSAGE_DISPLAY_OUTGOING, player.getDisplayName(), message);
                        player.sendTranslatable(TrKeys.MC_COMMANDS_MESSAGE_DISPLAY_INCOMING, sender.getCommandSenderName(), message);
                    }

                    return context.success();
                });
    }
}
