package org.allaymc.server.eventbus;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.EventException;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.server.Server;
import org.allaymc.server.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@RequiredArgsConstructor
public class AllayEventBus implements EventBus {

    protected final Map<Class<?>, List<AbstractEventHandler>> eventClassToHandlerMap = new Object2ObjectOpenHashMap<>();
    protected final Map<Object, List<AbstractEventHandler>> listenerToHandlerMap = new Object2ObjectOpenHashMap<>();
    protected final ExecutorService asyncExecutorService;

    public AllayEventBus() {
        this(Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public synchronized void registerListener(Object listener) {
        if (listenerToHandlerMap.containsKey(listener)) {
            return;
        }

        for (var method : ReflectionUtils.getAllMethods(listener.getClass())) {
            var annotation = method.getAnnotation(EventHandler.class);
            if (annotation == null) continue;
            if (method.getReturnType() != void.class) {
                throw new EventException("Event handler method must return void: " + method.getName() + " in listener " + listener.getClass().getName());
            }

            if (method.getParameterCount() != 1) {
                throw new EventException("Event handler method must have only one parameter: " + method.getName() + " in listener " + listener.getClass().getName());
            }

            var eventClass = method.getParameterTypes()[0];
            if (!Event.class.isAssignableFrom(eventClass)) {
                throw new EventException("Event handler method parameter must be a subclass of Event: " + method.getName() + " in listener " + listener.getClass().getName());
            }

            var handlers = eventClassToHandlerMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
            var handler = new MethodEventHandler(annotation.async(), annotation.priority(), eventClass, asyncExecutorService, method, listener);
            handlers.add(handler);
            handlers.sort((h1, h2) -> Integer.compare(h2.priority, h1.priority));
            listenerToHandlerMap.computeIfAbsent(listener, k -> new ArrayList<>()).add(handler);
        }
    }

    @Override
    public synchronized void unregisterListener(Object listener) {
        var handlers = listenerToHandlerMap.get(listener);
        if (handlers == null) {
            return;
        }

        handlers.forEach(handler -> eventClassToHandlerMap.get(handler.eventClass).remove(handler));
        listenerToHandlerMap.remove(listener);
    }

    @Override
    public synchronized <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async, int priority) {
        var handlers = eventClassToHandlerMap.computeIfAbsent(eventClass, k -> new ArrayList<>());
        var handler = new LambdaEventHandler<>(async, priority, eventClass, asyncExecutorService, eventConsumer);
        handlers.add(handler);
        handlers.sort((h1, h2) -> Integer.compare(h2.priority, h1.priority));
    }

    @Override
    public synchronized <E extends Event> void unregisterListenerFor(Class<E> eventClass, Consumer<E> eventConsumer) {
        var handlers = eventClassToHandlerMap.get(eventClass);
        if (handlers == null) {
            return;
        }

        handlers.removeIf(handler -> handler instanceof LambdaEventHandler<?> h && h.eventConsumer == eventConsumer);
    }

    @Override
    public <E extends Event> E callEvent(E event) {
        var handlers = eventClassToHandlerMap.get(event.getClass());
        if (handlers == null || handlers.isEmpty()) {
            return event;
        }

        handlers.forEach(handler -> handler.invoke(event));
        return event;
    }
}
