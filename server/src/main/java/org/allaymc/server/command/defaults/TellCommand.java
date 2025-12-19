package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.TrKeys;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class TellCommand extends Command {

    public TellCommand() {
        super("tell", TrKeys.MC_COMMANDS_TELL_DESCRIPTION, "allay.command.tell");
        this.aliases.add("msg");
        this.aliases.add("w");
    }

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
