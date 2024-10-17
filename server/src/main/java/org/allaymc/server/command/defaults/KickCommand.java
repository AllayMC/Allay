package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerKickEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

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
                .str("reason", "You are kicked")
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
                        Server.getInstance().getEventBus().callEvent(event);
                        if (event.isCancelled()) {
                            context.addError("PlayerKickEvent is cancelled");
                            return context.fail();
                        }
                        player.disconnect(event.getReason());
                        context.addOutput("Kicked " + name);
                    }
                    return context.success();
                });
    }
}
