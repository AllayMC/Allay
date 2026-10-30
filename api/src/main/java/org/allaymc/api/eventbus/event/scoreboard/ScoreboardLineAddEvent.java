package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * {@code ScoreboardLineAddEvent} represents a proposed insertion of a scoreboard line. A scoreboard
 * with events enabled dispatches this event before updating its scorer map. Cancellation prevents
 * storage and notification; allowing the event can replace a line for the same scorer.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ScoreboardLineAddEvent extends ScoreboardLineEvent implements CancellableEvent {
    /**
     * Creates an event retaining the supplied scoreboard and line references for listener
     * inspection.
     */
    public ScoreboardLineAddEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard, line);
    }
}
