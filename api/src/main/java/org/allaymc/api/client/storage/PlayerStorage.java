package org.allaymc.api.client.storage;

import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public interface PlayerStorage {
    /**
     * This method is called every tick.
     *
     * @param currentTick The current game tick.
     */
    default void tick(long currentTick) {}

    /**
     * This method is called when the server is shutting down.
     */
    default void shutdown() {}

    /**
     * Reads the player data for the given UUID.
     *
     * @param uuid The UUID of the player.
     *
     * @return The player data for the given UUID.
     */
    PlayerData readPlayerData(UUID uuid);

    /**
     * Reads the player data for the given player.
     *
     * @param player The player.
     *
     * @return The player data for the given player.
     */
    default PlayerData readPlayerData(EntityPlayer player) {
        return readPlayerData(player.getUUID());
    }

    /**
     * Saves the player data for the given UUID.
     *
     * @param uuid       The UUID of the player.
     * @param playerData The player data to save.
     */
    void savePlayerData(UUID uuid, PlayerData playerData);

    /**
     * Saves the player data for the given player.
     *
     * @param player The player.
     */
    default void savePlayerData(EntityPlayer player) {
        savePlayerData(player.getUUID(), player.savePlayerData());
    }

    /**
     * Removes the player data for the given UUID.
     *
     * @param uuid The UUID of the player.
     *
     * @return {@code true} if the player data was removed, {@code false} otherwise.
     */
    boolean removePlayerData(UUID uuid);

    /**
     * Checks if the player data exists for the given UUID.
     *
     * @param uuid The UUID of the player.
     *
     * @return {@code true} if the player data exists, {@code false} otherwise.
     */
    boolean hasPlayerData(UUID uuid);

    /**
     * Checks if the player data exists for the given player.
     *
     * @param player The player.
     *
     * @return {@code true} if the player data exists, {@code false} otherwise.
     */
    default boolean hasPlayerData(EntityPlayer player) {
        return hasPlayerData(player.getUUID());
    }
}
