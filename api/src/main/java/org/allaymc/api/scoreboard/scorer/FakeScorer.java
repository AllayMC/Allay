package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    public ScorerType getScorerType() {
        return ScorerType.FAKE;
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

}
