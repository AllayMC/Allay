package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * @author daoge_cmd
 */
public class ScoreboardRemoveEvent extends ScoreboardEvent implements CancellableEvent {
    public ScoreboardRemoveEvent(Scoreboard scoreboard) {
        super(scoreboard);
    }
}
