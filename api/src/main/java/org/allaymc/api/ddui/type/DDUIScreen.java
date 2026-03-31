package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.DDUIViewer;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;

/**
 * Base type of all DDUI screen templates.
 * <p>
 * A screen template is configured on the server, then turned into a runtime {@link DDUIScreenSession} when sent
 * to a {@link DDUIViewer}.
 *
 * @author daoge_cmd | SerenityJS
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
     * <p>
     * For example, this callback is used when the viewer dismisses a custom form or when the server closes the
     * screen programmatically.
     *
     * @param onClose the callback
     * @return this screen
     */
    public DDUIScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Convenience overload of {@link #onClose(BiConsumer)} that ignores the close reason.
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
