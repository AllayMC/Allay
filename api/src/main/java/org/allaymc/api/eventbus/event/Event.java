package org.allaymc.api.eventbus.event;

import lombok.Getter;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.server.Server;

/**
 * An abstract class to represent an event.
 *
 * @author daoge_cmd
 */
public abstract class Event {
    @Getter
    private boolean cancelled = false;

    /**
     * Call the event with the default event bus.
     *
     * @return {@code true} if the event was not cancelled, {@code false} otherwise.
     */
    public boolean call() {
        return call(Server.getInstance().getEventBus());
    }

    /**
     * Call the event with the specified event bus.
     *
     * @param eventBus the event bus to call the event.
     *
     * @return {@code true} if the event was not cancelled, {@code false} otherwise.
     */
    public boolean call(EventBus eventBus) {
        var event = eventBus.callEvent(this);
        return !event.isCancelled();
    }

    /**
     * Cancel the event.
     *
     * @param value {@code true} if the event is cancelled, otherwise {@code false}.
     *
     * @throws EventException if the event is not cancellable.
     */
    public void setCancelled(boolean value) {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        cancelled = value;
    }
}
