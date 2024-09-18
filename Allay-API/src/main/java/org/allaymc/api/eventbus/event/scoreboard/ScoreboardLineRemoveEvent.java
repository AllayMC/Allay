package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * @author daoge_cmd
 */
public class ScoreboardLineRemoveEvent extends ScoreboardLineEvent implements CancellableEvent {
    public ScoreboardLineRemoveEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard, line);
    }
}
