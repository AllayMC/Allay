package org.allaymc.api.eventbus;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.eventbus.event.Event;

import java.util.function.Consumer;

/**
 * The EventBus interface represents a system used for managing and dispatching events.
 * It allows for the registration and unregistration of listeners, as well as the
 * dispatching of events to appropriate listeners. This interface supports both synchronous
 * and asynchronous event calling, along with prioritized event handling.
 *
 * @author daoge_cmd
 */
public interface EventBus {
    AllayAPI.APIInstanceHolder<Factory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Create a new event bus.
     *
     * @return a new event bus
     */
    static EventBus create() {
        return FACTORY.get().create();
    }

    /**
     * Register a listener.
     *
     * @param listener the listener to register
     */
    void registerListener(Object listener);

    /**
     * Unregister a listener.
     *
     * @param listener the listener to unregister, should be the same object as the one registered
     */
    void unregisterListener(Object listener);

    /**
     * @see #registerListenerFor(Class, Consumer, boolean, int)
     */
    default <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer) {
        registerListenerFor(eventClass, eventConsumer, false, 0);
    }

    /**
     * @see #registerListenerFor(Class, Consumer, boolean, int)
     */
    default <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async) {
        registerListenerFor(eventClass, eventConsumer, async, 0);
    }

    /**
     * Register a listener for a specific event class.
     *
     * @param eventClass    the class of the event
     * @param eventConsumer the consumer to call when the event is called
     * @param async         whether the event should be called asynchronously
     * @param priority      the priority of the event
     */
    <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async, int priority);

    /**
     * Unregister a listener for a specific event class.
     *
     * @param eventClass    the class of the event
     * @param eventConsumer the consumer to unregister, should be the same object as the one registered
     */
    <E extends Event> void unregisterListenerFor(Class<E> eventClass, Consumer<E> eventConsumer);

    /**
     * Call an event.
     *
     * @param event the event to call
     * @param <E>   the type of the event
     * @return the event
     */
    <E extends Event> E callEvent(E event);

    /**
     * The factory for creating event buses.
     */
    interface Factory {
        EventBus create();
    }
}
