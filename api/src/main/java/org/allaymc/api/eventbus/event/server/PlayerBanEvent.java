package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerBanEvent} represents a request to ban a player UUID string or name.
 * The player manager dispatches this event before adding a previously absent
 * entry and disconnecting matching online players. Cancellation prevents the
 * requested ban and its associated disconnections.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class PlayerBanEvent extends ServerEvent implements CancellableEvent {
    /**
     * The UUID or name of the player being banned.
     */
    protected String uuidOrName;
}
