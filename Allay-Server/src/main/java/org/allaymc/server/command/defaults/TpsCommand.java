package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;

/**
 * Allay Project 2024/2/9
 *
 * @author daoge_cmd
 */
public class TpsCommand extends SimpleCommand {

    public TpsCommand() {
        super("tps", TrKeys.A_COMMAND_TPS_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("tps")
                .optional()
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
