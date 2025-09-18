package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.utils.TextFormat;

/**
 * @author daoge_cmd
 */
public class TpsCommand extends VanillaCommand {

    public TpsCommand() {
        super("tps", TrKeys.ALLAY_COMMAND_TPS_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().key("tps").optional().exec(context -> {
            context.getSender().sendMessage(TextFormat.GREEN + "TPS: " + context.getSender().getCommandExecuteLocation().dimension().getWorld().getTPS());
            return context.success();
        }).root().key("mspt").exec(context -> {
            context.getSender().sendMessage(TextFormat.GREEN + "MSPT: " + context.getSender().getCommandExecuteLocation().dimension().getWorld().getMSPT());
            return context.success();
        });
    }
}
