package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

import java.util.UUID;

import static org.cloudburstmc.protocol.bedrock.data.ScoreInfo.ScorerType.PLAYER;

/**
 * Allay Project 2024/2/27
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
        return Server.getInstance().getOnlinePlayers().get(uuid);
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
        return Server.getInstance().getOnlinePlayers().get(uuid) == null ?
                String.valueOf(uuid.getMostSignificantBits()) :
                Server.getInstance().getOnlinePlayers().get(uuid).getOriginName();
    }

    @Override
    public ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line) {
        if (uuid == null) return null;
        var player = Server.getInstance().getOnlinePlayers().get(uuid);
        return player != null ? new ScoreInfo(
                line.getLineId(),
                scoreboard.getObjectiveName(),
                line.getScore(),
                PLAYER,
                player.getRuntimeId()
        ) : null;
    }
}
