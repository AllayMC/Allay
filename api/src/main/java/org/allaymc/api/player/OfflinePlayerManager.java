package org.allaymc.api.player;

import java.util.UUID;

/**
 * Manager for managing offline player data and identity mappings.
 * <p>
 * This manager maintains a persistent index that maps various player identifiers
 * (XUID, nickname UUID, and nickname) to a storage UUID that uniquely identifies
 * each player's data. It handles player login, nickname changes, and collision
 * resolution while ensuring no player data is ever lost.
 *
 * <h2>Identity Resolution</h2>
 * Players are identified using multiple mappings stored in a LevelDB index:
 * <ul>
 *   <li><b>XUID mapping</b>: For Xbox-authenticated players (most reliable)</li>
 *   <li><b>Nickname UUID mapping</b>: UUID derived from lowercase nickname</li>
 *   <li><b>Nickname mapping</b>: Direct case-insensitive nickname lookup</li>
 * </ul>
 *
 * <h2>Login Flow</h2>
 * When a player logs in via {@link #handleUpdates(LoginData)}:
 * <ol>
 *   <li>If Xbox-authenticated: lookup by XUID (primary identifier)</li>
 *   <li>If found by XUID: handle any nickname changes and return existing player</li>
 *   <li>Otherwise: lookup by nickname UUID (offline mode)</li>
 *   <li>If not found anywhere: create new player with all mappings</li>
 * </ol>
 *
 * <h2>Nickname Collision Handling</h2>
 * When a nickname collision occurs (two players want the same nickname):
 * <ol>
 *   <li>The player changing TO the occupied nickname keeps it</li>
 *   <li>The previous owner is marked with "OriginalNickname" tag</li>
 *   <li>Previous owner gets a unique temporary nickname (base_uuid)</li>
 *   <li>Both players retain all their data - no data is lost</li>
 *   <li>A {@link org.allaymc.api.eventbus.event.player.PlayerNicknameChangeEvent} is fired</li>
 * </ol>
 *
 * @author IWareQ
 */
public interface OfflinePlayerManager {
    /**
     * Handles player login by creating new player data or loading existing data,
     * and updates all identity mappings accordingly.
     * <p>
     * This is the primary entry point for player authentication. It performs
     * identity resolution using XUID (if available) or nickname UUID, handles
     * nickname changes and collisions, and ensures all index mappings are current.
     *
     * @param loginData The login data containing XUID, nickname, and authentication info
     * @return The OfflinePlayer instance for this player (never null)
     */
    OfflinePlayer handleUpdates(LoginData loginData);

    /**
     * Handles player login using data from an active Player instance.
     *
     * @param player The player who is logging in
     * @return The OfflinePlayer instance for this player (never null)
     * @see #handleUpdates(LoginData)
     */
    default OfflinePlayer handleUpdates(Player player) {
        return this.handleUpdates(player.getLoginData());
    }

    /**
     * Retrieves an offline player by their Xbox User ID (XUID).
     * <p>
     * This is the most reliable way to look up Xbox-authenticated players,
     * as XUID is a permanent identifier that doesn't change even if the player
     * changes their nickname.
     *
     * @param xuid The Xbox User ID
     * @return The OfflinePlayer instance, or null if no player with this XUID exists
     */
    OfflinePlayer getByXboxUserId(long xuid);

    /**
     * Retrieves an offline player by their nickname-based UUID.
     * <p>
     * The nickname UUID is generated from the lowercase version of a player's
     * nickname using {@link UUID#nameUUIDFromBytes(byte[])}. This provides a
     * stable identifier for offline-mode players who don't have an XUID.
     *
     * @param nameUuid The UUID derived from the player's nickname
     * @return The OfflinePlayer instance, or null if not found
     */
    OfflinePlayer getByNicknameUuid(UUID nameUuid);

    /**
     * Retrieves an offline player by their current nickname.
     * <p>
     * The lookup is case-insensitive. Note that nicknames can change over time,
     * so this method may return different players if a nickname is transferred
     * due to collision handling.
     *
     * @param nickname The nickname to search for (case-insensitive)
     * @return The OfflinePlayer instance, or null if not found
     */
    OfflinePlayer getByNickname(String nickname);

    /**
     * Checks if a player with the given Xbox User ID exists in the system.
     *
     * @param xuid The Xbox User ID to check
     * @return true if a player with this XUID exists, false otherwise
     */
    default boolean hasPlayer(long xuid) {
        return this.getByXboxUserId(xuid) != null;
    }

    /**
     * Checks if a player with the given nickname UUID exists in the system.
     *
     * @param nicknameUuid The nickname-based UUID to check
     * @return true if a player with this UUID exists, false otherwise
     */
    default boolean hasPlayer(UUID nicknameUuid) {
        return this.getByNicknameUuid(nicknameUuid) != null;
    }

    /**
     * Checks if a player with the given nickname exists in the system.
     * <p>
     * The check is case-insensitive.
     *
     * @param nickname The nickname to check (case-insensitive)
     * @return true if a player with this nickname exists, false otherwise
     */
    default boolean hasPlayer(String nickname) {
        return this.getByNickname(nickname) != null;
    }

    /**
     * Saves all cached player data to persistent storage.
     * <p>
     * This method should be called periodically and definitely during server
     * shutdown to ensure no player data is lost. It iterates through all cached
     * players and writes their data to disk.
     */
    void saveAll();

    /**
     * Shuts down the offline player manager and releases all resources.
     * <p>
     * This should be called during server shutdown. After calling this method,
     * the manager should not be used again.
     */
    void shutdown();
}