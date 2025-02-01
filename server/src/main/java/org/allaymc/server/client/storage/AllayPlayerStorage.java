package org.allaymc.server.client.storage;

import org.allaymc.api.client.storage.PlayerStorage;

/**
 * @author daoge_cmd
 */
public abstract class AllayPlayerStorage implements PlayerStorage {
    public void tick(long currentTick) {}

    public void shutdown() {}
}
