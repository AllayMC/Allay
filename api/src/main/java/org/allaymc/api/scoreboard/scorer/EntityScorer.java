package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;

/**
 * EntityScorer is a scorer that represents an entity.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class EntityScorer implements Scorer {

    private final long uniqueId;

    public EntityScorer(Entity entity) {
        this(entity.getUniqueId().getLeastSignificantBits());
    }

    @Override
    public ScorerType getScorerType() {
        return ScorerType.ENTITY;
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

}
