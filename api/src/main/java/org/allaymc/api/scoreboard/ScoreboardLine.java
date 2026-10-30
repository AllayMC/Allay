package org.allaymc.api.scoreboard;

import lombok.Getter;
import org.allaymc.api.eventbus.event.scoreboard.ScoreboardLineValueChangeEvent;
import org.allaymc.api.scoreboard.scorer.Scorer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@code ScoreboardLine} represents a scorer and mutable score belonging to an objective.
 * Construction assigns a process-local client line identifier without inserting the line into its
 * owner. Score changes can be cancelled before storage, while successful changes notify the owning
 * scoreboard afterward.
 *
 * @author daoge_cmd
 */
@Getter
public final class ScoreboardLine {

    /**
     * {@code LINE_ID_COUNTER} allocates client line identifiers across newly constructed lines,
     * independently of objective names and scorer identities.
     */
    private static final AtomicLong LINE_ID_COUNTER = new AtomicLong(0);

    /**
     * Owning objective for events and updates.
     */
    private final Scoreboard scoreboard;
    /**
     * Scorer identity associated with this line.
     */
    private final Scorer scorer;
    /**
     * Process-local client line identifier.
     */
    private final long lineId;
    /**
     * Current numeric line score.
     */
    private int score;

    /**
     * Creates an uninserted line with score zero and a newly allocated client identifier. The
     * supplied scoreboard remains the line's owner for later score events and viewer notifications.
     */
    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer) {
        this(scoreboard, scorer, 0);
    }

    /**
     * Creates an uninserted line with the supplied score and a newly allocated client identifier.
     * The supplied scoreboard and scorer references are retained without adding the line to the
     * scoreboard's map.
     */
    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer, int score) {
        this.scoreboard = scoreboard;
        this.scorer = scorer;
        this.score = score;
        this.lineId = LINE_ID_COUNTER.incrementAndGet();
    }

    /**
     * Attempts to store a new score after any enabled value-change event permits it. Cancellation
     * returns {@code false} without storage or notification; viewer failure occurs after the new
     * score has been stored.
     */
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
     * Adds the supplied amount through the normal score-change operation, using Java integer
     * arithmetic. An enabled event can cancel the change; arithmetic overflow wraps before the
     * proposed score is dispatched.
     */
    public boolean addScore(int addition) {
        return setScore(getScore() + addition);
    }

    /**
     * Subtracts the supplied amount through the normal score-change operation, using Java integer
     * arithmetic. An enabled event can cancel the change; arithmetic overflow wraps before the
     * proposed score is dispatched.
     */
    public boolean removeScore(int reduction) {
        return setScore(getScore() - reduction);
    }

    /**
     * Notifies the owning scoreboard that this line's score should be sent to viewers.
     */
    private void updateScore() {
        scoreboard.updateScore(this);
    }
}
