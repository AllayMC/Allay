package org.allaymc.server.player.storage;

import org.allaymc.api.player.storage.PlayerStorage;

/**
 * @author daoge_cmd
 */
public abstract class AllayPlayerStorage implements PlayerStorage {
    public void tick(long currentTick) {
    }

    public void shutdown() {
    }
}
