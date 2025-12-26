package org.allaymc.server.player;

import lombok.Getter;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerStorage;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public abstract class AllayPlayerStorage implements PlayerStorage {

    @Getter
    protected final UserCache userCache = new UserCache();

    public void tick(long currentTick) {
    }

    public void shutdown() {
        userCache.save();
    }

    @Override
    public String getOfflinePlayerName(UUID uuid) {
        return userCache.getOfflinePlayerName(uuid);
    }

    @Override
    public UUID getOfflinePlayerUUID(String name) {
        return userCache.getOfflinePlayerUUID(name);
    }

    @Override
    public void cachePlayer(Player player) {
        userCache.add(player);
    }
}
