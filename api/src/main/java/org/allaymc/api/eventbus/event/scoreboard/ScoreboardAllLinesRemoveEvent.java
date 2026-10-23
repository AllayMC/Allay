package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * {@code ScoreboardAllLinesRemoveEvent} represents a proposed removal of every scoreboard line. A
 * scoreboard with events enabled dispatches this event before removing its lines. Cancellation
 * prevents the bulk operation; a sending removal can also dispatch individual line-removal events
 * afterward.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ScoreboardAllLinesRemoveEvent extends ScoreboardEvent implements CancellableEvent {
    /**
     * Creates an event retaining the supplied scoreboard reference for listener inspection.
     */
    public ScoreboardAllLinesRemoveEvent(Scoreboard scoreboard) {
        super(scoreboard);
    }
}
