package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.server.PlayerKickEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;

import java.util.List;

/**
 * {@code KickCommand} disconnects selected actual players after their individual
 * kick events are accepted. Simulated players are skipped. Cancellation stops
 * further processing without reconnecting earlier recipients, and an event's
 * replacement reason becomes the starting reason for the next selected player.
 *
 * @author daoge_cmd
 */
public class KickCommand extends Command {
    /**
     * Creates the {@code /kick} command with its built-in authorization permission.
     */
    public KickCommand() {
        super("kick", TrKeys.MC_COMMANDS_KICK_DESCRIPTION, Permissions.COMMAND_KICK);
    }

    /**
     * Installs kicking with an optional reason that defaults to an empty string.
     * A cancelled event returns failure immediately, preserving earlier
     * disconnections. Each accepted event may replace {@code reason}, and that
     * replacement is also supplied to subsequent targets' events.
     */
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
                        if (!player.isActualPlayer()) {
                            continue;
                        }

                        var controller = player.getController();
                        var event = new PlayerKickEvent(controller, reason);
                        // Earlier accepted targets are already disconnected.
                        // Stop on cancellation without attempting to undo
                        // those completed disconnections.
                        if (!event.call()) {
                            return context.fail();
                        }

                        reason = event.getReason();
                        var playerName = player.getDisplayName();
                        if (reason.isBlank()) {
                            controller.disconnect(I18n.get().tr(TrKeys.MC_DISCONNECT_KICKED));
                            context.addOutput(TrKeys.MC_COMMANDS_KICK_SUCCESS, playerName);
                        } else {
                            controller.disconnect(I18n.get().tr(TrKeys.MC_DISCONNECT_KICKED_REASON) + "\n" + reason);
                            context.addOutput(TrKeys.MC_COMMANDS_KICK_SUCCESS_REASON, playerName, reason);
                        }
                    }
                    return context.success();
                });
    }
}
