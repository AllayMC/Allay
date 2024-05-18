package org.allaymc.api.scoreboard.data;

import lombok.Getter;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 * <p>
 * Scoreboard display slot enum
 */
public enum DisplaySlot {
    // Right side of player's screen
    SIDEBAR("sidebar"),
    // Player list
    LIST("list"),
    // Below the player's name tag
    BELOW_NAME("belowname");

    @Getter
    private final String slotName;

    DisplaySlot(String slotName) {
        this.slotName = slotName;
    }
}
