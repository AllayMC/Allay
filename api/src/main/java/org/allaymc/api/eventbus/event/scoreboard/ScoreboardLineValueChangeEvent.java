package org.allaymc.api.eventbus.event.scoreboard;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * {@code ScoreboardLineValueChangeEvent} represents a proposed change to a scoreboard line score. A
 * scoreboard with events enabled dispatches this event before storing the new score. Cancellation
 * retains the old value and suppresses viewer updates; equal values can still produce the event.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class ScoreboardLineValueChangeEvent extends ScoreboardLineEvent implements CancellableEvent {
    /**
     * Score stored before the proposed change.
     */
    protected int oldValue;
    /**
     * Proposed replacement score for the line.
     */
    protected int newValue;

    /**
     * Creates an event retaining the supplied scoreboard and line references with the old and
     * proposed score values.
     */
    public ScoreboardLineValueChangeEvent(Scoreboard scoreboard, ScoreboardLine line, int oldValue, int newValue) {
        super(scoreboard, line);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
