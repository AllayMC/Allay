package org.allaymc.api.scoreboard;

import lombok.Getter;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardLineValueChangeEvent;
import org.allaymc.api.scoreboard.scorer.Scorer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A single line on the scoreboard.
 * <p>
 * Composed of {@link Scorer} and score.
 *
 * @author daoge_cmd
 */
@Getter
public final class ScoreboardLine {

    private static final AtomicLong LINE_ID_COUNTER = new AtomicLong(0);

    /**
     * The scoreboard to which this score line belongs
     */
    private final Scoreboard scoreboard;
    /**
     * Tracker
     */
    private final Scorer scorer;
    /**
     * Line ID, each line on the scoreboard is identified by this ID on the client
     */
    private final long lineId;
    private int score;

    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer) {
        this(scoreboard, scorer, 0);
    }

    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer, int score) {
        this.scoreboard = scoreboard;
        this.scorer = scorer;
        this.score = score;
        this.lineId = LINE_ID_COUNTER.incrementAndGet();
    }

    public boolean setScore(int score) {
        if (scoreboard.wouldCallEvent()) {
            var event = new ScoreboardLineValueChangeEvent(scoreboard, this, this.score, score);
            if (!event.call()) {
                return false;
            }
        }
        this.score = score;
        updateScore();
        return true;
    }

    /**
     * Increase the score
     *
     * @param addition Amount to increase
     * @return Whether it was successful (false if the event was cancelled)
     */
    public boolean addScore(int addition) {
        return setScore(getScore() + addition);
    }

    /**
     * Decrease the score
     *
     * @param reduction Amount to decrease
     * @return Whether it was successful (false if the event was cancelled)
     */
    public boolean removeScore(int reduction) {
        return setScore(getScore() - reduction);
    }

    /**
     * Notify the owning scoreboard object to update this line information
     */
    private void updateScore() {
        scoreboard.updateScore(this);
    }
}
