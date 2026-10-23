package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code IPUnbanEvent} represents a request to remove an existing IP address ban.
 * The player manager dispatches this event only for an address currently in
 * its ban set. Cancellation keeps that entry; acceptance removes it without
 * reconnecting any previously disconnected players.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class IPUnbanEvent extends ServerEvent implements CancellableEvent {
    /**
     * The IP address being unbanned.
     */
    protected String ip;
}
