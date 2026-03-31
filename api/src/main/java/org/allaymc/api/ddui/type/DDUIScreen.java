package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.DDUIViewer;
import org.allaymc.api.ddui.session.DDUIScreenSession;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;

/**
 * The base type of all DDUI screens.
 *
 * @author OpenAI
 */
public abstract class DDUIScreen {
    private BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose = (session, reason) -> {
    };

    /**
     * Sends this screen to the specified viewer.
     *
     * @param viewer the viewer that should open the screen
     * @return the created runtime session
     */
    public DDUIScreenSession sendTo(DDUIViewer viewer) {
        return viewer.viewScreen(this);
    }

    /**
     * Sets a callback that runs whenever this screen closes without producing a normal response.
     *
     * @param onClose the callback
     * @return this screen
     */
    public DDUIScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Sets a callback that runs whenever this screen closes without producing a normal response.
     *
     * @param onClose the callback
     * @return this screen
     */
    public DDUIScreen onClose(Runnable onClose) {
        return onClose((session, reason) -> onClose.run());
    }

    /**
     * Invokes the configured close callback.
     *
     * @param session the closed session
     * @param reason  the close reason
     */
    @ApiStatus.Internal
    public void handleClose(DDUIScreenSession session, DDUIScreenCloseReason reason) {
        onClose.accept(session, reason);
    }
}
