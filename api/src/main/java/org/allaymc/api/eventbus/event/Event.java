package org.allaymc.api.eventbus.event;

import lombok.Getter;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.server.Server;

/**
 * {@code Event} carries shared state through event-bus dispatch and reports
 * cancellation to
 * the code that raised the event. Cancellation is permitted only for subclasses
 * implementing {@link CancellableEvent}; it remains advisory for the caller and
 * handlers. Dispatch reuses this instance, and its plain cancellation field
 * supplies no separate synchronization for concurrent asynchronous handlers.
 *
 * @author daoge_cmd
 */
public abstract class Event {
    /**
     * {@code cancelled} records the current cancellation decision without
     * synchronization; the generated getter exposes that state and later handlers
     * may replace it.
     */
    @Getter
    private boolean cancelled = false;

    /**
     * Dispatches this event through the current server's default event bus.
     * The result reflects cancellation when dispatch returns, without waiting
     * for asynchronous handlers or resetting a cancellation value from an earlier call.
     *
     * @return {@code true} if the event was not cancelled, {@code false} otherwise.
     */
    public boolean call() {
        return call(Server.getInstance().getEventBus());
    }

    /**
     * Dispatches this instance through the supplied bus and reads its
     * cancellation flag afterward. The same instance can retain changes from
     * earlier calls, and asynchronous handlers are not awaited before the
     * boolean result is determined.
     *
     * @param eventBus the event bus to call the event
     * @return {@code true} if the event was not cancelled, {@code false} otherwise.
     */
    public boolean call(EventBus eventBus) {
        eventBus.callEvent(this);
        return !this.cancelled;
    }

    /**
     * Replaces this event's cancellation flag when its type supports cancellation.
     * Both setting and clearing the flag require {@link CancellableEvent}; other
     * event types reject the operation even when {@code value} is {@code false}.
     *
     * @param value {@code true} if the event should be cancelled, otherwise {@code false}
     * @throws EventException if the event is not cancellable
     */
    public void setCancelled(boolean value) {
        if (!(this instanceof CancellableEvent)) {
            throw new EventException("Event is not Cancellable");
        }
        cancelled = value;
    }
}
