package org.allaymc.api.scoreboard.scorer;

import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

/**
 * Describes a scoreboard tracking object
 * <p>
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 */
public sealed interface Scorer permits EntityScorer, PlayerScorer, FakeScorer {

    /**
     * Get the type of tracking object
     *
     * @return The type of tracking object
     */
    ScoreInfo.ScorerType getScorerType();

    /**
     * Get the name
     *
     * @return The name of the tracking object
     */
    String getName();

    /**
     * Internal method
     * <p>
     * Convert to network information
     *
     * @param scoreboard The associated scoreboard
     * @param line       The associated line
     *
     * @return The network information
     */
    ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line);
}
