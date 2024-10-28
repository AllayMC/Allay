package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;

import static java.lang.Runtime.getRuntime;
import static org.allaymc.api.utils.MathUtils.round;

/**
 * @author daoge_cmd
 */
public class GCCommand extends SimpleCommand {
    public GCCommand() {
        super("gc", TrKeys.A_COMMAND_GC_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec(context -> {
                    var memory = getCurrentMemoryUsage();
                    System.gc();
                    var freedMemory = memory - getCurrentMemoryUsage();
                    context.getSender().sendTr(TrKeys.A_COMMAND_GC_COMPLETED, freedMemory);
                    return context.success();
                });
    }

    protected double getCurrentMemoryUsage() {
        return round(((double) (getRuntime().totalMemory() - getRuntime().freeMemory())) / 1024 / 1024, 2);
    }
}
