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
import org.allaymc.api.world.WorldPool;

import java.util.concurrent.ExecutorService;

/**
 * {@code Server} exposes the server's lifecycle, shared services, and console
 * command identity.
 * API bootstrap publishes the server instance before normal startup completes;
 * callers use its lifecycle state to distinguish initialization, ticking, and
 * shutdown rather than treating instance availability as readiness.
 *
 * @author daoge_cmd
 */
public interface Server extends TaskCreator, CommandSender {

    /**
     * {@code INSTANCE} receives the server implementation during API bootstrap and exposes the
     * shared instance to service consumers through {@link #getInstance()}.
     */
    AllayAPI.APIInstanceHolder<Server> INSTANCE = AllayAPI.APIInstanceHolder.create();

    /**
     * Returns the server reference published by API bootstrap, or {@code null} beforehand.
     * Publication may precede startup completion, so a non-null result does not
     * imply that networking or world loading has finished.
     */
    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Returns wall-clock milliseconds recorded after network startup. The value
     * comes from {@link System#currentTimeMillis()} and remains zero until that
     * stage; it measures neither elapsed uptime nor object construction time.
     */
    long getStartTime();

    /**
     * Gets the server's scheduler which is running on the server tick.
     *
     * @return the server scheduler
     */
    Scheduler getScheduler();

    /**
     * Retrieves the plugin manager responsible for handling plugins.
     *
     * @return the plugin manager instance
     */
    PluginManager getPluginManager();

    /**
     * Returns the current server tick counter advanced by the main loop.
     *
     * @return the current tick count
     */
    long getTick();

    /**
     * Requests shutdown of a running server without waiting for cleanup.
     * The implementation changes {@code RUNNING} to {@code STOPPING}; its tick loop performs
     * cleanup afterward. Requests in other lifecycle states have no effect.
     */
    void shutdown();

    /**
     * Returns the current lifecycle state exposed by the server implementation.
     *
     * @return the server state
     */
    ServerState getState();

    /**
     * Returns the shared world pool responsible for loaded server worlds.
     */
    WorldPool getWorldPool();

    /**
     * Returns the shared executor for compute-oriented server work.
     * The server owns its lifetime and shuts it down during server cleanup;
     * consumers submit work without closing the executor themselves.
     */
    ExecutorService getComputeThreadPool();

    /**
     * Returns the shared executor that creates virtual threads for submitted work.
     * The server owns shutdown, so consumers must not close this executor or
     * assume submissions remain accepted after server cleanup begins.
     */
    ExecutorService getVirtualThreadPool();

    /**
     * Retrieves the player manager for managing players on the server.
     *
     * @return the player manager
     */
    PlayerManager getPlayerManager();

    /**
     * Returns the server's current shared broadcast channel for message receivers.
     */
    MessageChannel getMessageChannel();

    /**
     * Replaces the shared broadcast channel used by subsequent server consumers.
     * The implementation stores the supplied reference directly and does not
     * transfer receivers from the previous channel or automatically add the server.
     */
    void setMessageChannel(MessageChannel channel);

    /**
     * Returns the shared event bus used for server event dispatch.
     */
    EventBus getEventBus();

    /**
     * Returns the shared scoreboard manager responsible for server scoreboard state.
     */
    ScoreboardManager getScoreboardManager();
}
