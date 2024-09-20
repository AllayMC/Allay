package org.allaymc.api.eventbus.event;

/**
 * @author daoge_cmd
 */
public interface CancellableEvent {
    boolean isCancelled();

    void setCancelled(boolean value);

    default void cancel() {
        setCancelled(true);
    }
}
