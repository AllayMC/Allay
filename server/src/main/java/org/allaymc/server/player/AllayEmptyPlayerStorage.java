package org.allaymc.server.player;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.allaymc.api.player.PlayerData;

import java.util.UUID;

/**
 * {@code AllayEmptyPlayerStorage} provides a shared player-storage implementation
 * that discards every save. Each read creates data at the current global spawn
 * point, while existence and removal always report {@code false}; no per-player
 * state survives between calls.
 *
 * @author daoge_cmd
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllayEmptyPlayerStorage extends AllayPlayerStorage {

    /**
     * {@code INSTANCE} shares the stateless storage implementation across callers.
     * Reads derive fresh global-spawn data rather than retaining entries in this
     * singleton.
     */
    public static final AllayEmptyPlayerStorage INSTANCE = new AllayEmptyPlayerStorage();

    /**
     * Returns new default data located at the current global spawn point. The
     * supplied UUID is ignored, and the result contains no previously saved
     * inventory or other player-specific state.
     */
    @Override
    public PlayerData readPlayerData(UUID uuid) {
        return PlayerData.createEmpty();
    }

    /**
     * Discards the supplied player data without retaining or writing any state. The
     * supplied UUID has no effect, and later reads still create fresh default
     * global-spawn data.
     */
    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
    }

    /**
     * Returns {@code false} for every UUID because this storage retains no player
     * entries. The method performs no filesystem operation and does not change
     * subsequent default read results.
     */
    @Override
    public boolean removePlayerData(UUID uuid) {
        return false;
    }

    /**
     * Returns {@code false} for every UUID because this storage retains no player
     * entries. A subsequent read still returns default spawn data rather than
     * indicating a missing-data failure.
     */
    @Override
    public boolean hasPlayerData(UUID uuid) {
        return false;
    }
}
