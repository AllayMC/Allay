package org.allaymc.api.ddui;

import org.allaymc.api.ddui.session.DDUIScreenSession;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an object that can view a DDUI screen.
 *
 * @author OpenAI
 */
public interface DDUIViewer {
    /**
     * Opens a DDUI screen for this viewer.
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
    @Nullable
    DDUIScreenSession getActiveScreen();

    /**
     * Removes the DDUI screen that is currently active for this viewer without sending any close packet.
     *
     * @return the removed screen session, or {@code null} if no DDUI screen is active
     */
    @Nullable
    DDUIScreenSession removeActiveScreen();

    /**
     * Closes the DDUI screen that is currently active for this viewer.
     */
    void closeScreen();
}
