package org.allaymc.api.eventbus;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.eventbus.event.Event;

import java.util.function.Consumer;

/**
 * {@code EventBus} registers listeners and dispatches events to matching
 * handlers in priority order. Synchronous handlers run during dispatch, while
 * asynchronous handlers share the original event and can continue after dispatch
 * returns. The server implementation matches handlers by exact event class.
 *
 * @author daoge_cmd
 */
public interface EventBus {
    /**
     * {@code FACTORY} holds the runtime factory installed during API setup so callers can create
     * independent buses without selecting a server implementation directly.
     */
    AllayAPI.APIInstanceHolder<Factory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Creates an independent listener registry using the installed runtime
     * factory. The default server implementation borrows the server's executor
     * for asynchronous handlers; creating a bus does not start a separate executor.
     *
     * @return a new event bus
     */
    static EventBus create() {
        return FACTORY.get().create();
    }

    /**
     * Registers annotated methods discovered on a listener and its inherited
     * types. Handlers must return {@code void} and accept exactly one event parameter;
     * matching uses that exact event class. A listener already indexed by the
     * server implementation's equality-based listener map is not registered again.
     *
     * @param listener the listener to register
     */
    void registerListener(Object listener);

    /**
     * Removes handlers indexed for a previously registered listener.
     * An absent listener has no effect; removal does not retract handlers already
     * captured by an ongoing dispatch or submitted to an asynchronous executor.
     *
     * @param listener the listener to unregister, should be the same object as the one registered
     */
    void unregisterListener(Object listener);

    /**
     * Registers a consumer that runs synchronously at the default priority of
     * zero. Only events whose runtime class equals {@code eventClass} match;
     * registering the same consumer repeatedly creates separate handler registrations.
     *
     * @see #registerListenerFor(Class, Consumer, boolean, int)
     */
    default <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer) {
        registerListenerFor(eventClass, eventConsumer, false, 0);
    }

    /**
     * Registers a consumer at priority zero with the requested execution mode.
     * Asynchronous consumers receive the same event object as other handlers,
     * so callers must coordinate any mutable state they access after dispatch.
     *
     * @see #registerListenerFor(Class, Consumer, boolean, int)
     */
    default <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async) {
        registerListenerFor(eventClass, eventConsumer, async, 0);
    }

    /**
     * Registers a consumer for events of one exact runtime class.
     * Larger priorities are dispatched first, but asynchronous completion order
     * is determined by the executor. Consumers are invoked even for cancelled
     * events and repeated registration does not replace an earlier consumer.
     *
     * @param eventClass    the class of the event
     * @param eventConsumer the consumer to call when the event is called
     * @param async         whether the event should be called asynchronously
     * @param priority      the priority of the event
     */
    <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async, int priority);

    /**
     * Removes all registrations for the same consumer instance and event class.
     * Consumer identity, rather than equality, selects matches; missing
     * registrations are ignored, and removal does not cancel already submitted
     * asynchronous invocations.
     *
     * @param eventClass    the class of the event
     * @param eventConsumer the same consumer instance used during registration
     */
    <E extends Event> void unregisterListenerFor(Class<E> eventClass, Consumer<E> eventConsumer);

    /**
     * Dispatches an event to handlers registered for its exact runtime class
     * and returns the same object. Synchronous handlers finish before return;
     * asynchronous handlers may continue afterward, so the returned object's
     * mutable state is not a completion snapshot of all handlers.
     *
     * @param event the event to call
     * @param <E>   the type of the event
     * @return the event
     */
    <E extends Event> E callEvent(E event);

    /**
     * {@code Factory} creates event buses with runtime-defined executors and
     * independent listener registries.
     */
    interface Factory {
        /**
         * Creates an event bus with its own initially empty listener registry.
         */
        EventBus create();
    }
}
