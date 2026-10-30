package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerUnbanEvent} represents a request to remove an existing player
 * UUID or name ban.
 * The player manager dispatches this event only when the supplied entry is
 * currently banned. Cancellation retains that entry; acceptance removes it
 * without restoring any disconnected session.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class PlayerUnbanEvent extends ServerEvent implements CancellableEvent {
    /**
     * The UUID or name of the player being unbanned.
     */
    protected String uuidOrName;
}
