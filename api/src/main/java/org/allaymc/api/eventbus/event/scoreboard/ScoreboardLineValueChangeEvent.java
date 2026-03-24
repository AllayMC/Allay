package org.allaymc.api.eventbus.event.scoreboard;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class ScoreboardLineValueChangeEvent extends ScoreboardLineEvent implements CancellableEvent {
    /**
     * The old line value.
     */
    protected int oldValue;
    /**
     * The new line value.
     */
    protected int newValue;

    public ScoreboardLineValueChangeEvent(Scoreboard scoreboard, ScoreboardLine line, int oldValue, int newValue) {
        super(scoreboard, line);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
