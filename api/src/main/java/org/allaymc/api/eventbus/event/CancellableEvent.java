package org.allaymc.api.eventbus.event;

/**
 * An interface to represent a cancellable event.
 * <p>
 * Event that implements this interface is cancelled.
 *
 * @author daoge_cmd
 */
public interface CancellableEvent {
    /**
     * Check whether the event is cancelled.
     *
     * @return {@code true} if the event is cancelled, otherwise {@code false}.
     */
    boolean isCancelled();

    /**
     * Set the event to be cancelled or not.
     *
     * @param value {@code true} if the event is cancelled, otherwise {@code false}.
     */
    void setCancelled(boolean value);

    /**
     * Cancel the event.
     */
    default void cancel() {
        setCancelled(true);
    }
}
