package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code IPBanEvent} represents a request to ban an IP address that is not
 * already banned.
 * The player manager dispatches this event before updating its ban set or
 * disconnecting matching players. Cancellation prevents both effects for this
 * request and leaves the existing ban set unchanged.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class IPBanEvent extends ServerEvent implements CancellableEvent {
    /**
     * The IP address being banned.
     */
    protected String ip;
}
