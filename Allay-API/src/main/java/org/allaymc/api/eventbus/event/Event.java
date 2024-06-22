package org.allaymc.api.eventbus.event;

import lombok.Getter;
import org.allaymc.api.eventbus.EventException;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public abstract class Event {
    @Getter
    private boolean cancelled = false;

    public void setCancelled(boolean value) {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        cancelled = value;
    }
}
