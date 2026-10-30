package org.allaymc.api.scoreboard.scorer;

/**
 * {@code Scorer} identifies the subject of a scoreboard line independently of its score. Concrete
 * variants retain a player UUID, an entity identifier, or fake display text; their equality rules
 * determine which existing line a scoreboard lookup or insertion addresses.
 *
 * @author daoge_cmd
 */
public sealed interface Scorer permits EntityScorer, PlayerScorer, FakeScorer {

    /**
     * Returns the scorer category used for persistence and protocol encoding.
     */
    ScorerType getScorerType();

    /**
     * Returns the presentation name or numeric label supplied by this scorer implementation.
     */
    String getName();
}
