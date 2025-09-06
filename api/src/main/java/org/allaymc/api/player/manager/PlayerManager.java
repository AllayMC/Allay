package org.allaymc.api.player.manager;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.server.WhitelistChangeEvent;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.player.storage.PlayerStorage;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * PlayerManager is used to manage player related things. It holds all online players,
 * storage implementation for saving player data and ban/whitelist information.
 *
 * @author daoge_cmd
 */
public interface PlayerManager {

    /**
     * Get the online players.
     *
     * @return the online players
     */
    @UnmodifiableView
    Map<UUID, EntityPlayer> getPlayers();

    /**
     * Get the online player count of the server.
     *
     * @return the online player count of the server
     */
    default int getPlayerCount() {
        return getPlayers().size();
    }

    /**
     * Get the maximum online player count of the server.
     *
     * @return the maximum online player count of the server
     */
    int getMaxPlayerCount();

    /**
     * Set the maximum online player count of the server.
     *
     * @param value the maximum online player count of the server
     */
    void setMaxPlayerCount(int value);

    /**
     * Disconnect all players with the default reason.
     */
    default void disconnectAllPlayers() {
        disconnectAllPlayers(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnect all players.
     *
     * @param reason the reason of the disconnection
     */
    default void disconnectAllPlayers(@MayContainTrKey String reason) {
        getPlayers().values().forEach(player -> player.disconnect(reason));
    }

    /**
     * Get the used player storage.
     *
     * @return the used player storage
     */
    PlayerStorage getPlayerStorage();

    /**
     * Save the player data.
     */
    void savePlayerData();

    /**
     * Find an online player by his name.
     *
     * @param playerName the name of the player
     * @return the player if found, otherwise {@code null}
     */
    default EntityPlayer getOnlinePlayerByName(String playerName) {
        return getPlayers().values().stream()
                .filter(player -> player.getCommandSenderName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Broadcast a packet to all online players.
     *
     * @param packet the packet to broadcast
     */
    void broadcastPacket(BedrockPacket packet);

    /**
     * Check if the player is banned.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is banned, otherwise {@code false}.
     */
    boolean isBanned(String uuidOrName);

    /**
     * Ban the player.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is banned, otherwise {@code false}.
     */
    boolean ban(String uuidOrName);

    /**
     * Unban the player.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is unbanned, otherwise {@code false}.
     */
    boolean unban(String uuidOrName);

    /**
     * Get the banned players.
     *
     * @return the banned players
     */
    @UnmodifiableView
    Set<String> getBannedPlayers();

    /**
     * Check if the IP is banned.
     *
     * @param ip the IP to check
     * @return {@code true} if the IP is banned, otherwise {@code false}.
     */
    boolean isIPBanned(String ip);

    /**
     * Ban the IP.
     *
     * @param ip the IP to ban
     * @return {@code true} if the IP is banned, otherwise {@code false}.
     */
    boolean banIP(String ip);

    /**
     * Unban the IP.
     *
     * @param ip the IP to unban
     * @return {@code true} if the IP is unbanned, otherwise {@code false}.
     */
    boolean unbanIP(String ip);

    /**
     * Get the banned IPs.
     *
     * @return the banned IPs
     */
    @UnmodifiableView
    Set<String> getBannedIPs();

    /**
     * Set the whitelist status.
     *
     * @param enable {@code true} to enable the whitelist, otherwise {@code false}
     */
    default void setWhitelistStatus(boolean enable) {
        var event = new WhitelistChangeEvent(enable);
        if (!event.call()) {
            return;
        }

        Server.SETTINGS.genericSettings().isWhitelisted(enable);
        if (enable) {
            getPlayers().values().stream()
                    .filter(player -> !isWhitelisted(player))
                    .forEach(player -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTALLOWED));
        }
    }

    /**
     * Check if the player is in the whitelist.
     *
     * @param player the player to check
     * @return {@code true} if the player is in the whitelist, otherwise {@code false}.
     */
    default boolean isWhitelisted(EntityPlayer player) {
        return isWhitelisted(player.getLoginData().getUuid().toString()) || isWhitelisted(player.getOriginName());
    }

    /**
     * Check if the player is in the whitelist.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is in the whitelist, otherwise {@code false}.
     */
    boolean isWhitelisted(String uuidOrName);

    /**
     * Add the player to the whitelist.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is added to the whitelist, otherwise {@code false}.
     */
    boolean addToWhitelist(String uuidOrName);

    /**
     * Remove the player from the whitelist.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is removed from the whitelist, otherwise {@code false}.
     */
    boolean removeFromWhitelist(String uuidOrName);

    /**
     * Get the whitelisted players.
     *
     * @return the whitelisted players
     */
    @UnmodifiableView
    Set<String> getWhitelistedPlayers();

    /**
     * Get the network interface.
     *
     * @return the network interface
     */
    NetworkInterface getNetworkInterface();
}
