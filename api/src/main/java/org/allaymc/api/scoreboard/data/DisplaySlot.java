package org.allaymc.api.scoreboard.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code DisplaySlot} identifies a client location where an objective can be displayed. Each value
 * carries the protocol slot name used when sending scoreboard display or hide operations, while
 * persisted display associations use the enum identifier to record the selected location.
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
     * Player-list scoreboard display slot.
     */
    LIST("list"),
    /**
     * Below the player's name tag
     */
    BELOW_NAME("belowname");

    /**
     * Protocol display slot name.
     */
    private final String slotName;
}
