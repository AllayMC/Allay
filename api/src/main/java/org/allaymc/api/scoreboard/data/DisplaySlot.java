package org.allaymc.api.scoreboard.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Scoreboard display slot enum.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum DisplaySlot {
    /**
     * Right side of player's screen
     */
    SIDEBAR("sidebar"),
    /**
     * Player list
     */
    LIST("list"),
    /**
     * Below the player's name tag
     */
    BELOW_NAME("belowname");

    private final String slotName;
}
