package org.allaymc.api.ddui;

/**
 * Reasons delivered to {@link org.allaymc.api.ddui.type.DDUIScreen#onClose} when a DDUI screen ends without
 * producing a normal response.
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
