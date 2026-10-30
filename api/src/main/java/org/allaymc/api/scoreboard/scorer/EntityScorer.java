package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.Entity;

/**
 * {@code EntityScorer} represents a nonplayer entity using the least significant bits of its unique
 * identifier. Equality and hashing use that stored long, and its name is the decimal identifier
 * rather than a live entity lookup.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class EntityScorer implements Scorer {

    /**
     * Least-significant unique entity identifier bits.
     */
    private final long uniqueId;

    /**
     * Creates an entity scorer using the least significant unique identifier bits.
     */
    public EntityScorer(Entity entity) {
        this(entity.getUniqueId().getLeastSignificantBits());
    }

    /**
     * Returns the scorer category used when encoding this scoreboard entry.
     */
    @Override
    public ScorerType getScorerType() {
        return ScorerType.ENTITY;
    }

    /**
     * Returns a hash derived from this scorer's stored identity value.
     */
    @Override
    public int hashCode() {
        return Long.hashCode(uniqueId);
    }

    /**
     * Returns whether the other object has the same concrete scorer type and stored identity.
     * Equality does not compare current score values or objective ownership, so equal scorers
     * address the same map entry.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityScorer entityScorer) {
            return uniqueId == entityScorer.uniqueId;
        }
        return false;
    }

    /**
     * Returns the stored entity identifier formatted as a decimal string.
     */
    @Override
    public String getName() {
        return String.valueOf(uniqueId);
    }

}
