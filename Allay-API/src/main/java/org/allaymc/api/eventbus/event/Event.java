package org.allaymc.api.eventbus.event;

import lombok.Getter;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public abstract class Event {
    @Getter
    private boolean cancelled = false;

    public void call() {
        call(Server.getInstance().getEventBus());
    }

    public void call(EventBus eventBus) {
        eventBus.callEvent(this);
    }

    public void setCancelled(boolean value) {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        cancelled = value;
    }
}
