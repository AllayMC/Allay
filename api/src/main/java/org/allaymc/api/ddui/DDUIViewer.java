package org.allaymc.api.ddui;

import org.allaymc.api.ddui.type.DDUIScreen;

/**
 * {@code DDUIViewer} opens DDUI templates and tracks the viewer's active runtime session. A viewer
 * has at most one active session; opening another screen closes the previous session before
 * constructing and displaying its replacement.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUIViewer {
    /**
     * Opens the supplied screen template and returns its runtime session. The current session is
     * closed first, so a close callback failure or invalid replacement can prevent the new screen
     * from opening.
     */
    DDUIScreenSession viewScreen(DDUIScreen screen);

    /**
     * Returns the active DDUI session tracked for this viewer. The result is {@code null} when no
     * session is registered, and callers receive the actual runtime session rather than a snapshot.
     */
    DDUIScreenSession getActiveScreen();

    /**
     * Removes and returns the active-session reference without closing that session or sending
     * packets. Runtime handoff callers must perform any required cleanup themselves; {@code null}
     * means no session was registered.
     */
    DDUIScreenSession removeActiveScreen();

    /**
     * Closes the currently active DDUI session when one is registered. The session owns packet
     * delivery, subscription cleanup, and close callbacks; calling this operation with no active
     * session does nothing.
     */
    void closeScreen();
}
