package org.allaymc.server.client.storage;

import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.client.storage.PlayerStorage;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daoge_cmd
 */
public class AllayCachedPlayerStorage extends AllayPlayerStorage {

    public static final int CACHE_TIME = 20 * 60 * 5; // 5 minute

    protected PlayerStorage playerStorage;
    protected Map<UUID, DataEntry> cache = new ConcurrentHashMap<>();
    protected long currentTick;

    public AllayCachedPlayerStorage(PlayerStorage playerStorage) {
        this.playerStorage = playerStorage;
    }

    @Override
    public void tick(long currentTick) {
        this.currentTick = currentTick;
        cache.forEach((key, dataEntry) -> {
            if (currentTick - dataEntry.createTick() > CACHE_TIME) {
                cache.remove(key);
                playerStorage.savePlayerData(key, dataEntry.playerData());
            }
        });
    }

    @Override
    public PlayerData readPlayerData(UUID uuid) {
        var data = cache.get(uuid);
        if (data != null) return data.playerData;
        else {
            var playerData = playerStorage.readPlayerData(uuid);
            cache.put(uuid, new DataEntry(currentTick, playerData));
            return playerData;
        }
    }

    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        cache.put(uuid, new DataEntry(currentTick, playerData));
    }

    @Override
    public boolean removePlayerData(UUID uuid) {
        var cacheSuccess = cache.remove(uuid) != null;
        var success = playerStorage.removePlayerData(uuid);
        return cacheSuccess || success;
    }

    @Override
    public boolean hasPlayerData(UUID uuid) {
        return cache.containsKey(uuid) || playerStorage.hasPlayerData(uuid);
    }

    @Override
    public void shutdown() {
        cache.forEach((key, dataEntry) -> {
            cache.remove(key);
            playerStorage.savePlayerData(key, dataEntry.playerData);
        });
    }

    protected record DataEntry(long createTick, PlayerData playerData) {
    }
}
