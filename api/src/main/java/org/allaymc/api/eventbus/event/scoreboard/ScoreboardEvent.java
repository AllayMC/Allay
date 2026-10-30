package org.allaymc.api.eventbus.event.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * {@code ScoreboardEvent} represents the scoreboard reference shared by scoreboard events. Concrete
 * events describe changes to objectives or their lines. The stored scoreboard is the live object
 * targeted by the operation, allowing listeners to inspect its state before a cancellable mutation
 * occurs.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class ScoreboardEvent extends Event {
    /**
     * Live scoreboard targeted by the event.
     */
    protected Scoreboard scoreboard;
}
