package org.allaymc.server.event;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.allaymc.api.event.EventBus;
import org.allaymc.api.event.EventException;
import org.allaymc.api.event.EventHandler;
import org.allaymc.api.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class AllayEventBus implements EventBus {

    protected Map<Class<?>, List<Handler>> handlerMap = new Object2ObjectOpenHashMap<>();

    @Override
    public synchronized void registerListener(Object listener) {
        for (var method : ReflectionUtils.getAllMethods(listener.getClass())) {
            var annotation = method.getAnnotation(EventHandler.class);
            if (annotation == null) {
                continue;
            }
            if (!(method.getReturnType() == void.class)) {
                throw new EventException("Event handler method must return void: " + method.getName() + " in listener " + listener.getClass().getName());
            }
            if (method.getParameterCount() != 1) {
                throw new EventException("Event handler method must have only one parameter: " + method.getName() + " in listener " + listener.getClass().getName());
            }
            var eventClass = method.getParameterTypes()[0];
            if (!handlerMap.containsKey(eventClass)) {
                handlerMap.put(eventClass, new ArrayList<>());
            }
            handlerMap.get(eventClass).add(Handler.wrap(method, listener, annotation.async()));
        }
    }

    @Override
    public <E> E callEvent(E event) {
        var handlers = handlerMap.get(event.getClass());
        if (handlers == null || handlers.isEmpty()) return event;
        for (var handler : handlers) {
            handler.invoke(event);
        }
        return event;
    }
}
