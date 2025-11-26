package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;

import java.util.UUID;

/**
 * PlayerScorer is a scorer that represents a player.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class PlayerScorer implements Scorer {

    // Use UUID instead of uniqueId for convenience in obtaining player objects
    // This is essentially the same, as the player's uniqueId is generated from the UUID
    private final UUID uuid;

    public PlayerScorer(String uuid) {
        this(UUID.fromString(uuid));
    }

    public PlayerScorer(Player player) {
        this(player.getLoginData().getUuid());
    }

    public Player getPlayer() {
        if (uuid == null) {
            return null;
        }

        return Server.getInstance().getPlayerManager().getPlayers().get(uuid);
    }

    public boolean isOnline() {
        return getPlayer() != null;
    }

    @Override
    public ScorerType getScorerType() {
        return ScorerType.PLAYER;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerScorer playerScorer) {
            return uuid.equals(playerScorer.uuid);
        }
        return false;
    }

    @Override
    public String getName() {
        var player = Server.getInstance().getPlayerManager().getPlayers().get(uuid);
        return player == null ? String.valueOf(uuid.getMostSignificantBits()) : player.getOriginName();
    }

}
