package org.allaymc.api.eventbus.event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public interface CancellableEvent {

    boolean isCancelled();

    default void setCancelled() {
        setCancelled(true);
    }

    void setCancelled(boolean value);
}
