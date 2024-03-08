package org.allaymc.server.client.storage;

import org.allaymc.api.common.storage.PlayerData;
import org.allaymc.api.common.storage.PlayerStorage;

import java.util.UUID;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public class AllayEmptyPlayerStorage implements PlayerStorage {

    public static final AllayEmptyPlayerStorage INSTANCE = new AllayEmptyPlayerStorage();

    private AllayEmptyPlayerStorage() {}

    @Override
    public PlayerData readPlayerData(UUID uuid) {
        return PlayerData.createEmpty();
    }

    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        // Do nothing
    }

    @Override
    public boolean removePlayerData(UUID uuid) {
        return false;
    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return false;
    }

    @Override
    public void close() {
        // Do nothing
    }
}
