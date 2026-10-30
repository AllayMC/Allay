package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code WhitelistAddPlayerEvent} represents a request to add a player UUID or
 * name to the whitelist.
 * The player manager dispatches this event only for a previously absent entry.
 * Cancellation prevents insertion, while acceptance changes membership without
 * changing whether whitelist enforcement is enabled.
 *
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class WhitelistAddPlayerEvent extends ServerEvent implements CancellableEvent {
    /**
     * The UUID or name of the player being added to the whitelist.
     */
    protected String uuidOrName;
}
