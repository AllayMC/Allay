package org.allaymc.api.client.storage;

import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.UUID;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface PlayerStorage {
    default void tick(long currentTick) {}

    PlayerData readPlayerData(UUID uuid);

    default PlayerData readPlayerData(EntityPlayer player) {
        return readPlayerData(player.getUUID());
    }

    void savePlayerData(UUID uuid, PlayerData playerData);

    default void savePlayerData(EntityPlayer player) {
        savePlayerData(player.getUUID(), player.savePlayerData());
    }

    boolean removePlayerData(UUID uuid);

    boolean hasPlayerData(UUID uuid);

    default boolean hasPlayerData(EntityPlayer player) {
        return hasPlayerData(player.getUUID());
    }

    void close();
}
