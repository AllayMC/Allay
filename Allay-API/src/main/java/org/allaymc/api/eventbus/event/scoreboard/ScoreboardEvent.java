package org.allaymc.api.eventbus.event.scoreboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.scoreboard.Scoreboard;

/**
 * Allay Project 2024/8/31
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class ScoreboardEvent extends Event {
    protected Scoreboard scoreboard;
}
