package org.allaymc.api.server;

import eu.okaeri.configs.ConfigManager;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.client.service.PlayerService;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

/**
 * Represents the server instance.
 *
 * @author daoge_cmd
 */
public interface Server extends TaskCreator, CommandSender {

    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.create();

    /**
     * The settings file name.
     */
    String SETTINGS_FILE_NAME = "server-settings.yml";

    /**
     * The server settings.
     */
    ServerSettings SETTINGS = ConfigManager.create(ServerSettings.class, Utils.createConfigInitializer(Path.of(SETTINGS_FILE_NAME)));

    /**
     * Get the server instance.
     *
     * @return the server instance.
     */
    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Get the start time of the server.
     * <p>
     * The return value of this method is the result of {@link System#currentTimeMillis} when the server starts.
     *
     * @return the start time of the server.
     */
    long getStartTime();

    /**
     * Get the server scheduler.
     * <p>
     * If you want to do some world related operations, please use the scheduler of world instead,
     * because this scheduler is running on the server thread, doing world related operations in
     * this scheduler may have concurrency issues.
     * <p>
     * Use {@link World#getScheduler} to get the scheduler of the world.
     *
     * @return the server scheduler.
     */
    Scheduler getScheduler();

    /**
     * Get the plugin manager.
     *
     * @return the plugin manager.
     */
    PluginManager getPluginManager();

    /**
     * Get the current tick of the server.
     *
     * @return the current tick of the server.
     */
    long getTick();

    /**
     * Shutdown the server.
     */
    void shutdown();

    /**
     * Check if the server is running.
     *
     * @return {@code true} if the server is running, otherwise {@code false}.
     */
    boolean isRunning();

    /**
     * Check if the server is starting.
     *
     * @return {@code true} if the server is starting, otherwise {@code false}.
     */
    boolean isStarting();

    /**
     * Get the network interface.
     *
     * @return the network interface.
     */
    NetworkInterface getNetworkInterface();

    /**
     * Get the world pool.
     *
     * @return the world pool.
     */
    WorldPool getWorldPool();

    /**
     * Get the compute thread pool.
     *
     * @return the compute thread pool.
     */
    ExecutorService getComputeThreadPool();

    /**
     * Get the virtual thread pool.
     *
     * @return the virtual thread pool.
     */
    ExecutorService getVirtualThreadPool();

    /**
     * Get the player service of this server.
     *
     * @return the player service.
     */
    PlayerService getPlayerService();

    /**
     * Broadcast a text.
     *
     * @param text the text to broadcast.
     */
    default void broadcastText(String text) {
        getPlayerService().getOnlinePlayers().values().forEach(player -> player.sendText(text));
        sendText(text);
    }

    /**
     * Broadcast a translatable text.
     *
     * @param tr the translatable text to broadcast.
     */
    default void broadcastTr(@MayContainTrKey String tr) {
        broadcastTr(tr, new Object[0]);
    }

    /**
     * Broadcast a translatable text.
     *
     * @param tr   the translatable text to broadcast.
     * @param args the arguments of the translatable text.
     */
    void broadcastTr(@MayContainTrKey String tr, Object... args);

    @Override
    default void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot call setOp() on server instance!");
    }

    /**
     * Broadcast command outputs.
     *
     * @param sender  the command sender.
     * @param status  the return value of the execution of the command.
     * @param outputs the command outputs.
     */
    default void broadcastCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        sendCommandOutputs(sender, status, outputs);
        getPlayerService().getOnlinePlayers().values().forEach(player -> player.sendCommandOutputs(sender, status, outputs));
    }

    @Override
    default boolean hasPermission(String permission) {
        return true;
    }

    /**
     * Get the event bus.
     *
     * @return the event bus.
     */
    EventBus getEventBus();

    /**
     * Get the scoreboard service.
     *
     * @return the scoreboard service.
     */
    ScoreboardService getScoreboardService();
}
