package org.allaymc.api.eventbus.event.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * The base class for all scoreboard events.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class ScoreboardEvent extends Event {
    protected Scoreboard scoreboard;
}
