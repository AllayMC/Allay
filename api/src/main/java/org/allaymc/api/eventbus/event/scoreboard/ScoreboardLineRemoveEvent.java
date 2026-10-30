package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * {@code ScoreboardLineRemoveEvent} represents a proposed removal of an existing scoreboard line. A
 * scoreboard with events enabled dispatches this event before removing the line and notifying
 * viewers. Cancellation preserves that line; an absent scorer does not produce this event.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ScoreboardLineRemoveEvent extends ScoreboardLineEvent implements CancellableEvent {
    /**
     * Creates an event retaining the supplied scoreboard and line references for listener
     * inspection.
     */
    public ScoreboardLineRemoveEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard, line);
    }
}
