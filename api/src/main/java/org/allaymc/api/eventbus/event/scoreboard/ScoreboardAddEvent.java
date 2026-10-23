package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * {@code ScoreboardAddEvent} represents a proposed scoreboard registration. The manager dispatches
 * this event before storing the objective. Cancellation leaves its current objective map unchanged;
 * allowing the event can replace an existing objective with the same name.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.SERVER)
public class ScoreboardAddEvent extends ScoreboardEvent implements CancellableEvent {
    /**
     * Creates an event retaining the supplied scoreboard reference for listener inspection.
     */
    public ScoreboardAddEvent(Scoreboard scoreboard) {
        super(scoreboard);
    }
}
