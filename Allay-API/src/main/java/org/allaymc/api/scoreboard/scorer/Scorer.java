package org.allaymc.api.scoreboard.scorer;

import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

/**
 * Describes a scorer.
 *
 * @author daoge_cmd
 */
public sealed interface Scorer permits EntityScorer, PlayerScorer, FakeScorer {

    /**
     * Get the type of this scorer.
     *
     * @return The type of this scorer.
     */
    ScoreInfo.ScorerType getScorerType();

    /**
     * Get the name of this scorer.
     *
     * @return The name of this scorer.
     */
    String getName();

    /**
     * Convert this scorer to network information
     *
     * @param scoreboard The associated scoreboard
     * @param line The associated line
     *
     * @return The network information
     */
    ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line);
}
