package org.allaymc.api.scoreboard.scorer;

/**
 * Describes a scorer.
 *
 * @author daoge_cmd
 */
public sealed interface Scorer permits EntityScorer, PlayerScorer, FakeScorer {

    /**
     * Get the type of this scorer.
     *
     * @return The type of this scorer
     */
    ScorerType getScorerType();

    /**
     * Get the name of this scorer.
     *
     * @return The name of this scorer
     */
    String getName();
}
