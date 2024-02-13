package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class TpsCommand extends SimpleCommand {

    public TpsCommand() {
        // TODO: I18n
        super("tps", "Get the tps or mstp of current world");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("tps")
                .exec((context, player) -> {
                    player.sendText("§aTPS: " + player.getLocation().dimension().getWorld().getTps());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("mspt")
                .exec((context, player) -> {
                    player.sendText("§aMSPT: " + player.getLocation().dimension().getWorld().getMSPT());
                    return context.success();
                }, SenderType.PLAYER);
    }
}
