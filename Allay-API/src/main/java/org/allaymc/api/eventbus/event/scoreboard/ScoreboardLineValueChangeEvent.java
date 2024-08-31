package org.allaymc.api.eventbus.event.scoreboard;

import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
public class ScoreboardLineValueChangeEvent extends ScoreboardLineEvent implements CancellableEvent {
    protected int oldValue;
    protected int newValue;

    public ScoreboardLineValueChangeEvent(Scoreboard scoreboard, ScoreboardLine line, int oldValue, int newValue) {
        super(scoreboard, line);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
