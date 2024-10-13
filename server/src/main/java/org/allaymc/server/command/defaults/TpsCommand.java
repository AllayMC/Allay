package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.utils.TextFormat;

/**
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
                    player.sendText(TextFormat.GREEN + "TPS: " + player.getLocation().dimension().getWorld().getTPS());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("mspt")
                .exec((context, player) -> {
                    player.sendText(TextFormat.GREEN + "MSPT: " + player.getLocation().dimension().getWorld().getMSPT());
                    return context.success();
                }, SenderType.PLAYER);
    }
}
