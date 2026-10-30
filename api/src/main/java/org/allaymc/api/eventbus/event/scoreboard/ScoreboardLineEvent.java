package org.allaymc.api.eventbus.event.scoreboard;

import lombok.Getter;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;

/**
 * {@code ScoreboardLineEvent} represents the scoreboard and line references shared by line events.
 * Concrete events identify insertion, removal, or score changes. Both references remain live
 * objects rather than snapshots, so listeners inspect the operation's actual scoreboard and line.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class ScoreboardLineEvent extends ScoreboardEvent {
    /**
     * Live scoreboard line targeted by the event.
     */
    protected ScoreboardLine line;

    /**
     * Creates an event retaining the supplied scoreboard and line references for listener
     * inspection.
     */
    public ScoreboardLineEvent(Scoreboard scoreboard, ScoreboardLine line) {
        super(scoreboard);
        this.line = line;
    }
}
