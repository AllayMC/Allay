package org.allaymc.api.scoreboard.scorer;

/**
 * {@code ScorerType} identifies the representation used for a scoreboard scorer. Player, entity,
 * and fake variants select the matching identity data during encoding and persistence;
 * {@code INVALID} reserves a category that no permitted scorer implementation returns.
 *
 * @author daoge_cmd
 */
public enum ScorerType {
    /**
     * Unusable scorer category marker.
     */
    INVALID,
    /**
     * Persistent player UUID scorer.
     */
    PLAYER,
    /**
     * Unique entity identifier scorer.
     */
    ENTITY,
    /**
     * Arbitrary display text scorer.
     */
    FAKE
}
