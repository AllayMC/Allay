package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerKickEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class KickCommand extends SimpleCommand {
    public KickCommand() {
        super("kick", TrKeys.M_COMMANDS_KICK_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .str("reason")
                .optional()
                .exec(context -> {
                    List<EntityPlayer> players = context.getResult(0);
                    if (players.isEmpty()) {
                        context.addNoTargetMatchError();
                        return context.fail();
                    }
                    String reason = context.getResult(1);
                    for (var player : players) {
                        var event = new PlayerKickEvent(player, reason);
                        if (!event.call()) {
                            return context.fail();
                        }

                        reason = event.getReason();
                        if (reason.isBlank()) {
                            player.disconnect(I18n.get().tr(TrKeys.M_DISCONNECT_KICKED));
                            context.addOutput(TrKeys.M_COMMANDS_KICK_SUCCESS);
                        } else {
                            player.disconnect(I18n.get().tr(TrKeys.M_DISCONNECT_KICKED_REASON, reason));
                            context.addOutput(TrKeys.M_COMMANDS_KICK_SUCCESS_REASON, reason);
                        }
                    }
                    return context.success();
                });
    }
}
