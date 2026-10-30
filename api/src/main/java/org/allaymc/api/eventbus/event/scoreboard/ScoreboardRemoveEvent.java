package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * {@code ScoreboardRemoveEvent} represents a proposed scoreboard removal. The manager dispatches
 * this event before removing an existing objective. Cancellation leaves the objective registered
 * and prevents the subsequent viewer-removal and display-slot cleanup operations.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ScoreboardRemoveEvent extends ScoreboardEvent implements CancellableEvent {
    /**
     * Creates an event retaining the supplied scoreboard reference for listener inspection.
     */
    public ScoreboardRemoveEvent(Scoreboard scoreboard) {
        super(scoreboard);
    }
}
