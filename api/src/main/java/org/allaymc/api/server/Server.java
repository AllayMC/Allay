package org.allaymc.api.server;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.message.MessageChannel;
import org.allaymc.api.player.PlayerManager;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.scoreboard.ScoreboardManager;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;

import java.util.concurrent.ExecutorService;

/**
 * @author daoge_cmd
 */
public interface Server extends TaskCreator, CommandSender {

    /**
     * A static instance holder for the {@link Server} interface.
     */
    AllayAPI.APIInstanceHolder<Server> INSTANCE = AllayAPI.APIInstanceHolder.create();

    /**
     * Retrieves the server instance.
     *
     * @return the current server instance
     */
    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Retrieves the server's start time.
     *
     * <p>The returned value corresponds to the system time at which the server started,
     * obtained using {@link System#currentTimeMillis()}.</p>
     *
     * @return the server start time in milliseconds
     */
    long getStartTime();

    /**
     * Retrieves the server's scheduler.
     *
     * <p>For world-related operations, use the scheduler of the respective world instead,
     * as this scheduler runs on the server thread and may encounter concurrency issues
     * when modifying worlds.</p>
     *
     * @return the server scheduler
     * @see World#getScheduler()
     */
    Scheduler getScheduler();

    /**
     * Retrieves the plugin manager responsible for handling plugins.
     *
     * @return the plugin manager instance
     */
    PluginManager getPluginManager();

    /**
     * Retrieves the current server tick.
     *
     * @return the current tick count
     */
    long getTick();

    /**
     * Shutdown the server.
     */
    void shutdown();

    /**
     * Retrieves the current state of the server.
     *
     * @return the server state
     */
    ServerState getState();

    /**
     * Retrieves the world pool.
     *
     * @return the world pool instance
     */
    WorldPool getWorldPool();

    /**
     * Retrieves the compute thread pool.
     *
     * @return the compute thread pool
     */
    ExecutorService getComputeThreadPool();

    /**
     * Retrieves the virtual thread pool.
     *
     * @return the virtual thread pool
     */
    ExecutorService getVirtualThreadPool();

    /**
     * Retrieves the player manager for managing players on the server.
     *
     * @return the player manager
     */
    PlayerManager getPlayerManager();

    /**
     * Gets the message channel used in this server.
     *
     * @return the message channel used in this server
     */
    MessageChannel getMessageChannel();

    /**
     * Sets the message channel used in this server.
     *
     * @param channel the message channel used in this server
     */
    void setMessageChannel(MessageChannel channel);

    /**
     * Retrieves the event bus.
     *
     * @return the event bus instance
     */
    EventBus getEventBus();

    /**
     * Retrieves the scoreboard manager.
     *
     * @return the scoreboard manager instance
     */
    ScoreboardManager getScoreboardManager();
}
