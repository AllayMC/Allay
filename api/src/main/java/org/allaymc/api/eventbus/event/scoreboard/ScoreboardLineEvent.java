package org.allaymc.api.eventbus.event.scoreboard;

import lombok.Getter;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * @author daoge_cmd
 */
@Getter
public abstract class ScoreboardLineEvent extends ScoreboardEvent {
    protected ScoreboardLine line;

    public ScoreboardLineEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard);
        this.line = line;
    }
}
