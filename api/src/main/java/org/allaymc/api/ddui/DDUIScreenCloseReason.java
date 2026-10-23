package org.allaymc.api.ddui;

/**
 * {@code DDUIScreenCloseReason} identifies why a DDUI session ended without a normal response.
 * Screen close callbacks receive these values for explicit server closure or mapped client close
 * reasons, allowing callers to distinguish cancellation from a message-box button result.
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
