package org.allaymc.api.eventbus;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
public interface EventBus {
    ApiInstanceHolder<Factory> FACTORY = ApiInstanceHolder.create();

    static EventBus create() {
        return FACTORY.get().create();
    }

    void registerListener(Object listener);

    void unregisterListener(Object listener);

    <E extends Event> E callEvent(E event);

    interface Factory {
        EventBus create();
    }
}
