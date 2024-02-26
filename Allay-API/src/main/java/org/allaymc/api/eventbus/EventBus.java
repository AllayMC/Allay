package org.allaymc.api.eventbus;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public interface EventBus {

    ApiInstanceHolder<EventBusFactory> FACTORY = ApiInstanceHolder.create();

    static EventBus create() {
        return FACTORY.get().create();
    }

    void registerListener(Object listener);

    void unregisterListener(Object listener);

    <E extends Event> E callEvent(E event);

    interface EventBusFactory {
        EventBus create();
    }
}
