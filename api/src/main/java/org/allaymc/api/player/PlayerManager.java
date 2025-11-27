package org.allaymc.api.player;

import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.network.NetworkInterface;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

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
    Map<UUID, Player> getPlayers();

    /**
     * For-each the online players.
     *
     * @param consumer the consumer which will be applied to each player
     */
    default void forEachPlayer(Consumer<Player> consumer) {
        getPlayers().values().forEach(consumer);
    }

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
        forEachPlayer(player -> player.disconnect(reason));
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
    default Player getPlayerByName(String playerName) {
        return getPlayers().values().stream()
                .filter(player -> player.getOriginName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

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
     * Retrieves the current status of the whitelist.
     *
     * @return {@code true} if the whitelist is enabled, otherwise {@code false}.
     */
    boolean getWhitelistStatus();

    /**
     * Set the whitelist status.
     *
     * @param enable {@code true} to enable the whitelist, otherwise {@code false}
     */
    void setWhitelistStatus(boolean enable);

    /**
     * Check if the player is in the whitelist.
     *
     * @param player the player to check
     * @return {@code true} if the player is in the whitelist, otherwise {@code false}.
     */
    default boolean isWhitelisted(Player player) {
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
     * Checks if the specified player is an operator.
     *
     * @param player the player whose operator status is to be checked
     * @return {@code true} if the player is an operator, otherwise {@code false}
     */
    default boolean isOperator(Player player) {
        return isOperator(player.getLoginData().getUuid().toString()) || isOperator(player.getOriginName());
    }

    /**
     * Checks if the player, identified by their UUID or name, is an operator.
     *
     * @param uuidOrName the UUID or name of the player
     * @return {@code true} if the player is an operator, otherwise {@code false}
     */
    boolean isOperator(String uuidOrName);

    /**
     * Sets the operator status for a specific player identified by their UUID or name.
     *
     * @param uuidOrName the UUID or name of the player
     * @param value      {@code true} to set the player as an operator, {@code false} to remove operator status
     */
    void setOperator(String uuidOrName, boolean value);

    /**
     * Get the network interface.
     *
     * @return the network interface
     */
    NetworkInterface getNetworkInterface();
}
