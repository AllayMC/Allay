package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

/**
 * FakeScorer is a scorer that only has a name and is not associated with any entity.
 * <p>
 * FakeScorer is used to display some information through scoreboard.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class FakeScorer implements Scorer {

    private final String fakeName;

    @Override
    public ScoreInfo.ScorerType getScorerType() {
        return ScoreInfo.ScorerType.FAKE;
    }

    @Override
    public int hashCode() {
        return fakeName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FakeScorer fakeScorer) {
            return fakeScorer.fakeName.equals(fakeName);
        }
        return false;
    }

    @Override
    public String getName() {
        return fakeName;
    }

    @Override
    public ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line) {
        return new ScoreInfo(line.getLineId(), scoreboard.getObjectiveName(), line.getScore(), fakeName);
    }
}
