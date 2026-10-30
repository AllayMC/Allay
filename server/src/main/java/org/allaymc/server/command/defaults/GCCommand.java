package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;

import static java.lang.Runtime.getRuntime;
import static org.allaymc.api.math.MathUtils.round;

/**
 * {@code GCCommand} requests immediate unused-chunk removal, entity autosave
 * checks, and JVM garbage collection. The command compares rounded heap usage
 * before and after those requests and reports the difference as an estimate;
 * the garbage-collection request does not guarantee a particular amount of
 * reclaimed memory.
 *
 * @author daoge_cmd
 */
public class GCCommand extends Command {
    /**
     * Creates the {@code /gc} command with its built-in authorization permission.
     */
    public GCCommand() {
        super("gc", TrKeys.ALLAY_COMMAND_GC_DESCRIPTION, Permissions.COMMAND_GC);
    }

    /**
     * Installs cleanup requests across every loaded world's dimensions.
     * Chunk removal and entity autosave checks run before {@link System#gc()},
     * and feedback reports the change between rounded heap-usage readings
     * without waiting for a guaranteed collection result.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().exec(context -> {
            var memory = getCurrentMemoryUsage();
            for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
                for (var dimension : world.getDimensions().values()) {
                    dimension.getChunkManager().removeUnusedChunksImmediately();
                    dimension.getEntityManager().checkAutoSaveImmediately();
                }
            }
            System.gc();
            var freedMemory = memory - getCurrentMemoryUsage();
            context.getSender().sendTranslatable(TrKeys.ALLAY_COMMAND_GC_COMPLETED, String.format("%.2f", freedMemory));
            return context.success();
        });
    }

    /**
     * Returns used JVM heap memory in mebibytes, rounded to two decimals.
     */
    protected double getCurrentMemoryUsage() {
        // Binary conversion keeps feedback in mebibytes before decimal rounding.
        return round(((double) (getRuntime().totalMemory() - getRuntime().freeMemory())) / 1024 / 1024, 2);
    }
}
