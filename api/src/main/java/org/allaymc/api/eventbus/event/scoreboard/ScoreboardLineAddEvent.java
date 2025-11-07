package org.allaymc.api.eventbus.event.scoreboard;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.UNKNOWN)
public class ScoreboardLineAddEvent extends ScoreboardLineEvent implements CancellableEvent {
    public ScoreboardLineAddEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard, line);
    }
}
