package org.allaymc.api.ddui;

import org.allaymc.api.ddui.type.DDUIScreen;

/**
 * Something that can open and own DDUI screen sessions.
 * <p>
 * A viewer has at most one active DDUI session at a time. Opening a new screen replaces the current one.
 *
 * @author daoge_cmd | SerenityJS
 */
public interface DDUIViewer {
    /**
     * Opens a DDUI screen for this viewer.
     * <p>
     * If another DDUI screen is already active, it is closed before the new screen is shown.
     *
     * @param screen the screen to open
     * @return the created runtime session
     */
    DDUIScreenSession viewScreen(DDUIScreen screen);

    /**
     * Gets the DDUI screen that is currently active for this viewer.
     *
     * @return the active screen session, or {@code null} if no DDUI screen is active
     */
    DDUIScreenSession getActiveScreen();

    /**
     * Removes the DDUI screen that is currently active for this viewer without sending any close packet.
     * <p>
     * This is a low-level operation intended for runtime handoff code.
     *
     * @return the removed screen session, or {@code null} if no DDUI screen is active
     */
    DDUIScreenSession removeActiveScreen();

    /**
     * Closes the DDUI screen that is currently active for this viewer, if any.
     */
    void closeScreen();
}
