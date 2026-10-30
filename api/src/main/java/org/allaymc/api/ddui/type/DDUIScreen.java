package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.DDUIViewer;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;

/**
 * {@code DDUIScreen} represents a reusable template opened as a viewer-specific DDUI session. The
 * template stores its close callback, while the viewer owns session replacement and the runtime
 * session supplies the close reason when invoking that callback.
 *
 * @author daoge_cmd | SerenityJS
 */
public abstract class DDUIScreen {
    /**
     * {@code onClose} receives sessions that close without a normal response. Callback failures
     * propagate to the session operation invoking it.
     */
    private BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose = (session, reason) -> {
    };

    /**
     * Opens this template through the supplied viewer and returns the created session. The viewer
     * closes its previous active screen first, and failures during that closure can prevent this
     * template from opening.
     */
    public DDUIScreenSession sendTo(DDUIViewer viewer) {
        return viewer.viewScreen(this);
    }

    /**
     * Stores the callback for session closure without a normal screen response and returns this
     * template. Callbacks receive the session and reason; their exceptions propagate after the
     * session's preceding close steps.
     */
    public DDUIScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Stores a close callback that ignores the session and close reason. The wrapper invokes the
     * supplied action when closure is reported, and any action failure propagates to that close
     * operation.
     */
    public DDUIScreen onClose(Runnable onClose) {
        return onClose((session, reason) -> onClose.run());
    }

    /**
     * Invokes the configured callback with the supplied session and close reason. This dispatch
     * performs no cleanup or exception recovery itself, so callers complete their required close
     * bookkeeping before invoking it.
     */
    @ApiStatus.Internal
    public void handleClose(DDUIScreenSession session, DDUIScreenCloseReason reason) {
        onClose.accept(session, reason);
    }
}
