package org.allaymc.api.event;

import org.allaymc.api.ApiInstanceHolder;

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

    <E> E callEvent(E event);

    interface EventBusFactory {
        EventBus create();
    }
}
