package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

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

    public PlayerScorer(EntityPlayer player) {
        this(player.getUUID());
    }

    public EntityPlayer getPlayer() {
        if (uuid == null) return null;
        return Server.getInstance().getPlayerService().getPlayers().get(uuid);
    }

    public boolean isOnline() {
        return getPlayer() != null;
    }

    @Override
    public ScoreInfo.ScorerType getScorerType() {
        return ScoreInfo.ScorerType.PLAYER;
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
        var player = Server.getInstance().getPlayerService().getPlayers().get(uuid);
        return player == null ? String.valueOf(uuid.getMostSignificantBits()) : player.getOriginName();
    }

    @Override
    public ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line) {
        if (uuid == null) return null;
        var player = Server.getInstance().getPlayerService().getPlayers().get(uuid);
        return player != null ? new ScoreInfo(
                line.getLineId(),
                scoreboard.getObjectiveName(),
                line.getScore(),
                ScoreInfo.ScorerType.PLAYER,
                player.getRuntimeId()
        ) : null;
    }
}
