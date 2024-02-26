package org.allaymc.api.event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public abstract class Event {
    private boolean cancelled = false;

    public boolean isCancelled() {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        return cancelled;
    }

    public void setCancelled(boolean value) {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        cancelled = value;
    }
}
