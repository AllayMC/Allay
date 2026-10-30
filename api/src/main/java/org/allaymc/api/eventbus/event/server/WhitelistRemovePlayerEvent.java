package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code WhitelistRemovePlayerEvent} represents a request to remove an existing
 * whitelist entry for a player.
 * Cancellation preserves membership and prevents the accompanying disconnects.
 * On acceptance, the player manager removes the entry and disconnects matching
 * online players, including when whitelist enforcement is currently disabled.
 *
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.SERVER)
public class WhitelistRemovePlayerEvent extends ServerEvent implements CancellableEvent {
    /**
     * The UUID or name of the player being removed from the whitelist.
     */
    protected String uuidOrName;
}
