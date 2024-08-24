package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.utils.TextFormat;

import static java.lang.Runtime.getRuntime;
import static org.allaymc.api.utils.MathUtils.round;

/**
 * Allay Project 2024/8/24
 *
 * @author daoge_cmd
 */
public class GCCommand extends SimpleCommand {
    public GCCommand() {
        // TODO: i18n
        super("gc", "Perform a JVM garbage collection immediately");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec(context -> {
                    var memory = getCurrentMemoryUsage();
                    System.gc();
                    var freedMemory = memory - getCurrentMemoryUsage();
                    context.getSender().sendText("Memory freed: " + TextFormat.GREEN + freedMemory + " MB");
                    return context.success();
                });
    }

    protected double getCurrentMemoryUsage() {
        return round((double) (getRuntime().totalMemory() - getRuntime().freeMemory()) / 1024 / 1024, 2);
    }
}
