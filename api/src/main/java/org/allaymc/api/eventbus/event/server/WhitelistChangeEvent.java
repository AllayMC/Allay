package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code WhitelistChangeEvent} represents a request to set the server's
 * whitelist enforcement status.
 * The player manager emits this event even when the requested status matches
 * the current status. Acceptance stores the requested value and, when enabled,
 * disconnects players who are not whitelisted; cancellation skips both steps.
 *
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class WhitelistChangeEvent extends ServerEvent implements CancellableEvent {
    /**
     * {@code enable} describes the requested whitelist enforcement status. This
     * value represents the pending setting rather than the server's current setting.
     */
    protected boolean enable;
}
