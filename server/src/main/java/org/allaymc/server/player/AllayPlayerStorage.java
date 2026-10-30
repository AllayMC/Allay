package org.allaymc.server.player;

import org.allaymc.api.player.PlayerStorage;

/**
 * {@code AllayPlayerStorage} adds server lifecycle hooks to the player-storage
 * contract. The base hooks perform no work, allowing simple synchronous stores to
 * inherit them; storage implementations needing periodic maintenance or shutdown
 * cleanup override the corresponding hook.
 *
 * @author daoge_cmd
 */
public abstract class AllayPlayerStorage implements PlayerStorage {
    /**
     * Accepts the current server tick without performing base storage maintenance.
     */
    public void tick(long currentTick) {
    }

    /**
     * Finishes base storage lifecycle handling without performing any cleanup work.
     */
    public void shutdown() {
    }
}
