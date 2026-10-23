package org.allaymc.api.player;

import java.util.UUID;

/**
 * {@code PlayerStorage} reads, saves, and removes player data by persistent UUID.
 * Convenience methods resolve the UUID from login data, and saving a connection
 * captures its current {@code PlayerData}; storage implementations define durability
 * and failure handling for those synchronous calls.
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {

    /**
     * Returns player data associated with the supplied persistent identity.
     * Implementations may create default spawn data when no usable entry exists;
     * callers must not assume a successful read proves prior storage.
     */
    PlayerData readPlayerData(UUID uuid);

    /**
     * Reads data using the player's resolved login UUID. Login data must already be
     * available, and the selected storage implementation decides how missing entries
     * and read failures are handled.
     */
    default PlayerData readPlayerData(Player player) {
        return readPlayerData(player.getLoginData().getUuid());
    }

    /**
     * Saves the supplied data under the persistent player UUID. The storage
     * implementation defines durability and failure reporting; this interface does
     * not promise an atomic replacement or automatic recovery.
     */
    void savePlayerData(UUID uuid, PlayerData playerData);

    /**
     * Captures the connection's current player data and saves it under its login
     * UUID. Callers must provide initialized login data and coordinate access to the
     * mutable entity being captured.
     */
    default void savePlayerData(Player player) {
        savePlayerData(player.getLoginData().getUuid(), PlayerData.save(player));
    }

    /**
     * Removes any stored entry for the supplied player UUID. The result is
     * {@code true} when an entry is removed and {@code false} when the
     * implementation removes nothing.
     */
    boolean removePlayerData(UUID uuid);

    /**
     * Returns whether this storage reports an entry for the supplied UUID. Existence
     * does not establish that its contents are valid or that a later read will
     * succeed.
     */
    boolean hasPlayerData(UUID uuid);

    /**
     * Checks storage existence using the player's resolved login UUID. Login data
     * must already be available, and the result does not validate the stored payload
     * or guarantee later readability.
     */
    default boolean hasPlayerData(Player player) {
        return hasPlayerData(player.getLoginData().getUuid());
    }
}
