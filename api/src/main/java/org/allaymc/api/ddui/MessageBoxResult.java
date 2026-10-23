package org.allaymc.api.ddui;

/**
 * {@code MessageBoxResult} identifies which message-box button the viewer selected. A message-box
 * session reports a button result through response callbacks; closing the screen without selecting
 * a button instead uses the separate screen-close callback and reason.
 *
 * @author daoge_cmd | SerenityJS
 */
public enum MessageBoxResult {
    /**
     * The first button was selected.
     */
    BUTTON1,
    /**
     * The second button was selected.
     */
    BUTTON2
}
