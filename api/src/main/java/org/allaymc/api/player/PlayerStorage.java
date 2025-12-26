package org.allaymc.api.player;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public interface PlayerStorage {

    /**
     * Reads the player data for the given UUID.
     *
     * @param uuid The UUID of the player
     * @return The player data for the given UUID
     */
    PlayerData readPlayerData(UUID uuid);

    /**
     * Reads the player data for the given player.
     *
     * @param player The player
     * @return The player data for the given player
     */
    default PlayerData readPlayerData(Player player) {
        return readPlayerData(player.getLoginData().getUuid());
    }

    /**
     * Saves the player data for the given UUID.
     *
     * @param uuid       The UUID of the player
     * @param playerData The player data to save
     */
    void savePlayerData(UUID uuid, PlayerData playerData);

    /**
     * Saves the player data for the given player.
     *
     * @param player The player
     */
    default void savePlayerData(Player player) {
        savePlayerData(player.getLoginData().getUuid(), PlayerData.save(player));
    }

    /**
     * Removes the player data for the given UUID.
     *
     * @param uuid The UUID of the player
     * @return {@code true} if the player data was removed, {@code false} otherwise.
     */
    boolean removePlayerData(UUID uuid);

    /**
     * Checks if the player data exists for the given UUID.
     *
     * @param uuid The UUID of the player
     * @return {@code true} if the player data exists, {@code false} otherwise.
     */
    boolean hasPlayerData(UUID uuid);

    /**
     * Checks if the player data exists for the given player.
     *
     * @param player The player
     * @return {@code true} if the player data exists, {@code false} otherwise.
     */
    default boolean hasPlayerData(Player player) {
        return hasPlayerData(player.getLoginData().getUuid());
    }

    /**
     * Get the name of an offline player.
     *
     * @param uuid the UUID of the player
     * @return the name of the player if found, otherwise {@code null}
     */
    String getOfflinePlayerName(UUID uuid);

    /**
     * Get the UUID of an offline player.
     *
     * @param name the name of the player
     * @return the UUID of the player if found, otherwise {@code null}
     */
    UUID getOfflinePlayerUUID(String name);
}
