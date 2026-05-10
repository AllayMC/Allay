package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player is added to the whitelist.
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
