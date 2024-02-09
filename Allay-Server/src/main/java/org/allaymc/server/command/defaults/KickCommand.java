package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class KickCommand extends SimpleCommand {

    public KickCommand() {
        // TODO: I18n
        super("kick", "Kick a player");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("kick")
                // TODO: Should use target(), but target() can only match players in default world
                .str("player")
                .exec(context -> {
                    String name = context.getSecondResult();
                    for (var player : Server.getInstance().getOnlinePlayers().values()) {
                        if (player.getName().equals(name)) {
                            player.disconnect("You are kicked");
                            context.addOutput("Kicked " + name);
                            return context.success();
                        }
                    }
                    context.addOutput("§cUnknown player: " + name);
                    return context.failed();
                });
    }
}
