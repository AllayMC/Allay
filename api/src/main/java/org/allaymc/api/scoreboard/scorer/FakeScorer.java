package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code FakeScorer} represents an arbitrary named scoreboard entry with no entity association.
 * Equality and hashing use the supplied text, allowing repeated entries with equal text to address
 * the same line in a scorer-keyed objective.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class FakeScorer implements Scorer {

    /**
     * Text identifying the fake scorer.
     */
    private final String fakeName;

    /**
     * Returns the scorer category used when encoding this scoreboard entry.
     */
    @Override
    public ScorerType getScorerType() {
        return ScorerType.FAKE;
    }

    /**
     * Returns a hash derived from this scorer's stored identity value.
     */
    @Override
    public int hashCode() {
        return fakeName.hashCode();
    }

    /**
     * Returns whether the other object has the same concrete scorer type and stored identity.
     * Equality does not compare current score values or objective ownership, so equal scorers
     * address the same map entry.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FakeScorer fakeScorer) {
            return fakeScorer.fakeName.equals(fakeName);
        }
        return false;
    }

    /**
     * Returns the text supplied as this fake scorer's identity.
     */
    @Override
    public String getName() {
        return fakeName;
    }

}
