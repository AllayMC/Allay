package org.allaymc.api.ddui;

/**
 * Represents the reason why a DDUI screen session was closed.
 *
 * @author daoge_cmd | SerenityJS
 */
public enum DDUIScreenCloseReason {
    /**
     * The server closed the current screen by targeting its form id.
     */
    PROGRAMMATIC,
    /**
     * The server closed all DDUI screens for the viewer.
     */
    PROGRAMMATIC_ALL,
    /**
     * The viewer closed the screen.
     */
    CLOSED,
    /**
     * The viewer was busy and could not keep the screen open.
     */
    BUSY,
    /**
     * The screen session became invalid.
     */
    INVALID
}
