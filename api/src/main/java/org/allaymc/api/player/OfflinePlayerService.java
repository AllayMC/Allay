package org.allaymc.api.player;

import java.util.UUID;

/**
 * @author IWareQ
 */
public interface OfflinePlayerService {
    /**
     * Handles player login - creates a new player or loads existing data,
     * and updates all mappings accordingly.
     *
     * <p>This method should be called when a player logs in to the server.
     * It will:
     * <ul>
     *   <li>Create a new player if they've never joined before</li>
     *   <li>Load existing player data if they have</li>
     *   <li>Handle nickname changes and collisions</li>
     *   <li>Update all index mappings (xuid, nameuuid, nickname)</li>
     * </ul>
     *
     * @param loginData The login data from the player's connection
     * @return The OfflinePlayer instance for this player
     */
    OfflinePlayer handleUpdates(LoginData loginData);

    /**
     * Handles player login.
     * Convenience method that extracts LoginData from Player.
     *
     * @param player The player who is logging in
     * @return The OfflinePlayer instance for this player
     */
    default OfflinePlayer handleUpdates(Player player) {
        return this.handleUpdates(player.getLoginData());
    }

    /**
     * Gets an offline player by their Xbox User ID.
     *
     * @param xuid The Xbox User ID to search for
     * @return The OfflinePlayer instance, or null if not found
     */
    OfflinePlayer getByXboxUserId(long xuid);

    /**
     * Gets an offline player by their name-based UUID.
     *
     * @param nameUuid The name-based UUID to search for
     * @return The OfflinePlayer instance, or null if not found
     */
    OfflinePlayer getByNicknameUuid(UUID nameUuid);

    /**
     * Gets an offline player by their nickname.
     * The search is case-insensitive.
     *
     * @param nickname The nickname to search for
     * @return The OfflinePlayer instance, or null if not found
     */
    OfflinePlayer getByNickname(String nickname);

    /**
     * Checks if a player with the given Xbox User ID exists.
     *
     * @param xuid The Xbox User ID to check
     * @return true if a player with this UUID exists, false otherwise
     */
    default boolean hasPlayer(long xuid) {
        return this.getByXboxUserId(xuid) != null;
    }

    default boolean hasPlayer(UUID nicknameUuid) {
        return this.getByNicknameUuid(nicknameUuid) != null;
    }

    /**
     * Checks if a player with the given nickname exists.
     * The check is case-insensitive.
     *
     * @param nickname The nickname to check
     * @return true if a player with this nickname exists, false otherwise
     */
    default boolean hasPlayer(String nickname) {
        return this.getByNickname(nickname) != null;
    }

    /**
     * Saves all player data to disk.
     * This is typically called during server shutdown.
     */
    void saveAll();

    /**
     * Closes the offline player service and releases resources.
     * This should be called during server shutdown.
     */
    void shutdown();
}
