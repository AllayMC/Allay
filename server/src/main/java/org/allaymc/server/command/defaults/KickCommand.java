package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerKickEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class KickCommand extends VanillaCommand {
    public KickCommand() {
        super("kick", TrKeys.MC_COMMANDS_KICK_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().playerTarget("player").str("reason").optional().exec(context -> {
            List<EntityPlayer> players = context.getResult(0);
            if (players.isEmpty()) {
                context.addNoTargetMatchError();
                return context.fail();
            }

            String reason = context.getResult(1);
            for (var player : players) {
                if (!player.isActualPlayer()) {
                    continue;
                }

                var event = new PlayerKickEvent(player, reason);
                if (!event.call()) {
                    return context.fail();
                }

                reason = event.getReason();
                if (reason.isBlank()) {
                    player.getController().disconnect(I18n.get().tr(TrKeys.MC_DISCONNECT_KICKED));
                    context.addOutput(TrKeys.MC_COMMANDS_KICK_SUCCESS);
                } else {
                    player.getController().disconnect(I18n.get().tr(TrKeys.MC_DISCONNECT_KICKED_REASON, reason));
                    context.addOutput(TrKeys.MC_COMMANDS_KICK_SUCCESS_REASON, reason);
                }
            }
            return context.success();
        });
    }
}
