package org.allaymc.api.eventbus.event;

/**
 * {@code CancellableEvent} identifies events whose cancellation decision can
 * be changed by handlers.
 * Implementing this interface permits cancellation but does not initially
 * cancel an event. The caller raising the event decides how to respond, while
 * handlers can inspect the current decision or clear it before dispatch returns.
 *
 * @author daoge_cmd
 */
public interface CancellableEvent {
    /**
     * Returns whether the event's current cancellation flag is set.
     *
     * @return {@code true} if the event is cancelled, otherwise {@code false}.
     */
    boolean isCancelled();

    /**
     * Replaces the event's cancellation decision with the supplied value.
     * Clearing a previously set decision permits later code to treat the event
     * as uncancelled; individual handlers may still have already observed its
     * earlier state.
     *
     * @param value {@code true} if the event is cancelled, otherwise {@code false}
     */
    void setCancelled(boolean value);

    /**
     * Sets the cancellation decision to {@code true} through the implementing setter.
     * This requests cancellation rather than stopping dispatch itself; handlers
     * that accept cancelled events can still receive and modify the same event.
     */
    default void cancel() {
        setCancelled(true);
    }
}
