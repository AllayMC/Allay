package org.allaymc.server.client.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.client.storage.PlayerStorage;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllayEmptyPlayerStorage implements PlayerStorage {

    public static final AllayEmptyPlayerStorage INSTANCE = new AllayEmptyPlayerStorage();

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
}
