package org.allaymc.api.eventbus;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
public interface EventBus {
    ApiInstanceHolder<Factory> FACTORY = ApiInstanceHolder.create();

    /**
     * Create a new event bus.
     *
     * @return a new event bus.
     */
    static EventBus create() {
        return FACTORY.get().create();
    }

    /**
     * Register a listener.
     *
     * @param listener the listener to register.
     */
    void registerListener(Object listener);

    /**
     * Unregister a listener.
     *
     * @param listener the listener to unregister.
     */
    void unregisterListener(Object listener);

    /**
     * Call an event.
     *
     * @param event the event to call.
     * @param <E>   the type of the event.
     *
     * @return the event.
     */
    <E extends Event> E callEvent(E event);

    interface Factory {
        EventBus create();
    }
}
