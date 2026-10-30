package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.utils.TextFormat;

/**
 * {@code TpsCommand} displays tick rate or milliseconds per tick for the sender's
 * execution world. The optional {@code tps} branch is the default operation,
 * while an explicit {@code mspt} branch selects the other metric; both send
 * the current reported value directly to the sender.
 *
 * @author daoge_cmd
 */
public class TpsCommand extends Command {

    /**
     * Creates the {@code /tps} command with its built-in authorization permission.
     */
    public TpsCommand() {
        super("tps", TrKeys.ALLAY_COMMAND_TPS_DESCRIPTION, Permissions.COMMAND_TPS);
    }

    /**
     * Installs tick-rate display by default and an explicit millisecond metric.
     * Both branches read the sender's current execution world when invoked and
     * send feedback directly without changing that world's tick behavior.
     */
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
