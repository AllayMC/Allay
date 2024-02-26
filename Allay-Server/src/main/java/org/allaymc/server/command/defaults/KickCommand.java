package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class KickCommand extends SimpleCommand {

    public KickCommand() {
        super("kick", TrKeys.M_COMMANDS_KICK_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                // TODO: Should use target(), but target() can only match players in default world
                .str("player")
                .exec(context -> {
                    String name = context.getSecondResult();
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        if (player.getCommandSenderName().equals(name)) {
                            player.disconnect("You are kicked");
                            context.addOutput("Kicked " + name);
                            return context.success();
                        }
                    }
                    context.addError("Unknown player: " + name);
                    return context.failed();
                });
    }
}
