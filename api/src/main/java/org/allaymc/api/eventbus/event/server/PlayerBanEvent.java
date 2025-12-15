package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player is banned from the server.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class PlayerBanEvent extends ServerEvent implements CancellableEvent {
    protected String uuidOrName;
}
