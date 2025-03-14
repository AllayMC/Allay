package org.allaymc.api.server;

import eu.okaeri.configs.ConfigManager;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.client.storage.PlayerStorage;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.server.WhitelistChangeEvent;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
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
     * Disconnect all players with the default reason.
     */
    default void disconnectAllPlayers() {
        disconnectAllPlayers(TrKeys.M_DISCONNECT_CLOSED);
    }

    /**
     * Disconnect all players.
     *
     * @param reason the reason of the disconnection.
     */
    default void disconnectAllPlayers(@MayContainTrKey String reason) {
        getOnlinePlayers().values().forEach(player -> player.disconnect(reason));
    }

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
     * Get the player storage.
     *
     * @return the player storage.
     */
    PlayerStorage getPlayerStorage();

    /**
     * Get the online player count of the server.
     *
     * @return the online player count of the server.
     */
    default int getOnlinePlayerCount() {
        return getOnlinePlayers().size();
    }

    /**
     * Get the network interface.
     *
     * @return the network interface.
     */
    NetworkInterface getNetworkInterface();

    /**
     * Get the online players.
     *
     * @return the online players.
     */
    @UnmodifiableView
    Map<UUID, EntityPlayer> getOnlinePlayers();

    /**
     * Get the world pool.
     *
     * @return the world pool.
     */
    WorldPool getWorldPool();

    /**
     * Broadcast a packet to all online players.
     *
     * @param packet the packet to broadcast.
     */
    void broadcastPacket(BedrockPacket packet);

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
     * Broadcast a text.
     *
     * @param text the text to broadcast.
     */
    default void broadcastText(String text) {
        getOnlinePlayers().values().forEach(player -> player.sendText(text));
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
        getOnlinePlayers().values().forEach(player -> player.sendCommandOutputs(sender, status, outputs));
    }

    /**
     * Save the player data.
     */
    void savePlayerData();

    @Override
    default boolean hasPermission(String permission) {
        return true;
    }

    /**
     * Check if the player is banned.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is banned, otherwise {@code false}.
     */
    boolean isBanned(String uuidOrName);

    /**
     * Ban the player.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is banned, otherwise {@code false}.
     */
    boolean ban(String uuidOrName);

    /**
     * Unban the player.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is unbanned, otherwise {@code false}.
     */
    boolean unban(String uuidOrName);

    /**
     * Get the banned players.
     *
     * @return the banned players.
     */
    @UnmodifiableView
    Set<String> getBannedPlayers();

    /**
     * Check if the IP is banned.
     *
     * @param ip the IP to check.
     *
     * @return {@code true} if the IP is banned, otherwise {@code false}.
     */
    boolean isIPBanned(String ip);

    /**
     * Ban the IP.
     *
     * @param ip the IP to ban.
     *
     * @return {@code true} if the IP is banned, otherwise {@code false}.
     */
    boolean banIP(String ip);

    /**
     * Unban the IP.
     *
     * @param ip the IP to unban.
     *
     * @return {@code true} if the IP is unbanned, otherwise {@code false}.
     */
    boolean unbanIP(String ip);

    /**
     * Get the banned IPs.
     *
     * @return the banned IPs.
     */
    @UnmodifiableView
    Set<String> getBannedIPs();

    /**
     * Set the whitelist status.
     *
     * @param enable {@code true} to enable the whitelist, otherwise {@code false}.
     */
    default void setWhitelistStatus(boolean enable) {
        var event = new WhitelistChangeEvent(enable);
        if (!event.call()) return;

        SETTINGS.genericSettings().isWhitelisted(enable);
        if (enable) {
            getOnlinePlayers().values().stream()
                    .filter(player -> !isWhitelisted(player))
                    .forEach(player -> player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED));
        }
    }

    /**
     * Check if the player is in the whitelist.
     *
     * @param player the player to check.
     *
     * @return {@code true} if the player is in the whitelist, otherwise {@code false}.
     */
    default boolean isWhitelisted(EntityPlayer player) {
        return isWhitelisted(player.getUUID().toString()) || isWhitelisted(player.getOriginName());
    }

    /**
     * Check if the player is in the whitelist.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is in the whitelist, otherwise {@code false}.
     */
    boolean isWhitelisted(String uuidOrName);

    /**
     * Add the player to the whitelist.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is added to the whitelist, otherwise {@code false}.
     */
    boolean addToWhitelist(String uuidOrName);

    /**
     * Remove the player from the whitelist.
     *
     * @param uuidOrName the UUID or name of the player.
     *
     * @return {@code true} if the player is removed from the whitelist, otherwise {@code false}.
     */
    boolean removeFromWhitelist(String uuidOrName);

    /**
     * Get the whitelisted players.
     *
     * @return the whitelisted players.
     */
    @UnmodifiableView
    Set<String> getWhitelistedPlayers();

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

    /**
     * Get the online player by his name.
     *
     * @param name the name of the player.
     *
     * @return the player if found, otherwise {@code null}.
     */
    default EntityPlayer getOnlinePlayerByName(String name) {
        return getOnlinePlayers().values().stream()
                .filter(player -> player.getOriginName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
