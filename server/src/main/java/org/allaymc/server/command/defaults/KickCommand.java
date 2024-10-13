package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.eventbus.event.player.PlayerKickEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

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
                // TODO: Should use target(), but target() can only match players in sender's world
                .str("player")
                .str("reason", "You are kicked")
                .optional()
                .exec(context -> {
                    String name = context.getResult(0);
                    String reason = context.getResult(1);
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        if (player.getDisplayName().equals(name)) {
                            var event = new PlayerKickEvent(player, reason);
                            Server.getInstance().getEventBus().callEvent(event);
                            if (event.isCancelled()) {
                                context.addError("PlayerKickEvent is cancelled");
                                return context.fail();
                            }
                            player.disconnect(event.getReason());
                            context.addOutput("Kicked " + name);
                            return context.success();
                        }
                    }

                    context.addError("Unknown player: " + name);
                    return context.fail();
                });
    }
}
