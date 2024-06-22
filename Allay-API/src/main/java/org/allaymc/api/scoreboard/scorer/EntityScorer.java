package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class EntityScorer implements Scorer {

    private final long uniqueId;

    public EntityScorer(Entity entity) {
        this(entity.getUniqueId());
    }

    @Override
    public ScoreInfo.ScorerType getScorerType() {
        return ScoreInfo.ScorerType.ENTITY;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(uniqueId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityScorer entityScorer) {
            return uniqueId == entityScorer.uniqueId;
        }
        return false;
    }

    @Override
    public String getName() {
        return String.valueOf(uniqueId);
    }

    @Override
    public ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line) {
        return new ScoreInfo(
                line.getLineId(),
                scoreboard.getObjectiveName(),
                line.getScore(),
                ScoreInfo.ScorerType.ENTITY,
                uniqueId
        );
    }
}
