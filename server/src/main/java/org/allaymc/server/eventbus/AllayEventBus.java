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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * {@code AllayEventBus} registers method and consumer handlers and dispatches
 * events by exact runtime class. Handler lists are ordered by descending priority, while a
 * second index associates method handlers with their listener for later
 * removal. The generated constructor accepts a borrowed asynchronous executor;
 * the default constructor uses the server's virtual-thread pool.
 *
 * <p>
 * Registration mutations synchronize on the bus, and copy-on-write handler
 * lists let a dispatch retain its current traversal when a callback registers
 * or removes listeners. {@code eventClassToHandlerMap} and
 * {@code listenerToHandlerMap} remain ordinary maps, however, and dispatch does
 * not acquire the registration monitor. Callers must coordinate
 * cross-thread registration and dispatch rather than inferring complete thread
 * safety from those handler lists. Asynchronous callbacks share event instances
 * and can finish after dispatch has returned.
 *
 * @author daoge_cmd
 */
@RequiredArgsConstructor
public class AllayEventBus implements EventBus {

    /**
     * {@code eventClassToHandlerMap} maps exact event classes to priority-sorted
     * copy-on-write lists. Empty lists remain indexed after all matching handlers
     * have been removed.
     */
    protected final Map<Class<?>, List<AbstractEventHandler>> eventClassToHandlerMap = new Object2ObjectOpenHashMap<>();
    /**
     * {@code listenerToHandlerMap} indexes method handlers by listener equality
     * for duplicate suppression and removal. Consumer-only registrations do not
     * populate this index.
     */
    protected final Map<Object, List<AbstractEventHandler>> listenerToHandlerMap = new Object2ObjectOpenHashMap<>();
    /**
     * {@code asyncExecutorService} supplies asynchronous execution without
     * transferring executor ownership to the bus. Its caller must coordinate
     * shutdown with outstanding handler invocations.
     */
    protected final ExecutorService asyncExecutorService;

    /**
     * Creates an empty bus using the current server's virtual-thread executor.
     * A server instance must already be installed; the bus borrows that pool
     * and does not manage its shutdown.
     */
    public AllayEventBus() {
        this(Server.getInstance().getVirtualThreadPool());
    }

    /**
     * Discovers and validates annotated methods before indexing each handler.
     * An equality-matching listener already in the reverse index is ignored.
     * Methods discovered through class and interface inheritance must return
     * {@code void} and accept exactly one {@link Event} subtype; malformed declarations
     * throw {@link EventException}.
     *
     * <p>
     * This synchronized operation protects mutations against other registration
     * operations, but installs handlers incrementally. If a later method fails
     * validation or handle creation, earlier handlers and their reverse-index
     * entries remain registered rather than being rolled back.
     */
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

            var handlers = eventClassToHandlerMap.computeIfAbsent(eventClass, k -> new CopyOnWriteArrayList<>());
            var handler = new MethodEventHandler(
                    annotation.async(),
                    annotation.priority(),
                    annotation.ignoreCancelled(),
                    eventClass,
                    asyncExecutorService,
                    method,
                    listener
            );

            // Publish each method in both indexes under the registration monitor;
            // listener removal needs the same handle that dispatch stores for this event class.
            handlers.add(handler);
            handlers.sort((h1, h2) -> Integer.compare(h2.priority, h1.priority));
            listenerToHandlerMap.computeIfAbsent(listener, k -> new ArrayList<>()).add(handler);
        }
    }

    /**
     * Removes the method handlers indexed for an equality-matching listener.
     * A missing listener is ignored. Removal changes future handler traversals
     * but neither retracts a dispatch snapshot already in progress nor cancels
     * callbacks previously submitted to the executor.
     */
    @Override
    public synchronized void unregisterListener(Object listener) {
        var handlers = listenerToHandlerMap.get(listener);
        if (handlers == null) {
            return;
        }

        // Remove the indexed handles before forgetting their owner, keeping
        // subsequent registration and removal operations from leaving orphaned method handlers in event lists.
        handlers.forEach(handler -> eventClassToHandlerMap.get(handler.eventClass).remove(handler));
        listenerToHandlerMap.remove(listener);
    }

    /**
     * Adds a consumer handler to the list for one exact event class.
     * Registration always adds another handler, including for an already
     * registered consumer instance, and consumer handlers do not filter cancelled
     * events. Higher priorities are visited earlier during dispatch.
     */
    @Override
    public synchronized <E extends Event> void registerListenerFor(Class<E> eventClass, Consumer<E> eventConsumer, boolean async, int priority) {
        // Keep insertion and priority sorting within the registration monitor
        // so concurrent registration operations cannot overwrite each other's intended handler ordering.
        var handlers = eventClassToHandlerMap.computeIfAbsent(eventClass, k -> new CopyOnWriteArrayList<>());
        var handler = new LambdaEventHandler<>(async, priority, eventClass, asyncExecutorService, eventConsumer);
        handlers.add(handler);
        handlers.sort((h1, h2) -> Integer.compare(h2.priority, h1.priority));
    }

    /**
     * Removes consumer handlers whose stored consumer is the supplied instance.
     * An unknown event class is ignored, and all duplicate registrations of
     * that identical consumer are removed without affecting method-based handlers.
     */
    @Override
    public synchronized <E extends Event> void unregisterListenerFor(Class<E> eventClass, Consumer<E> eventConsumer) {
        var handlers = eventClassToHandlerMap.get(eventClass);
        if (handlers == null) {
            return;
        }

        // Match consumer identity rather than equality so removal targets the
        // exact callback registrations retained by the caller, without affecting unrelated equivalent consumers.
        handlers.removeIf(handler -> handler instanceof LambdaEventHandler<?> h && h.eventConsumer == eventConsumer);
    }

    /**
     * Dispatches the original event through its exact runtime-class handler
     * list and returns that reference. The copy-on-write traversal retains its
     * current handler snapshot when a callback changes registration; such
     * changes affect later traversals instead. Asynchronous handlers are only
     * submitted, so their completion and cancellation observations can occur
     * after this method returns.
     *
     * <p>
     * Dispatch does not lock the ordinary backing map. Callers must coordinate
     * concurrent registration with lookup; the list's snapshot behavior alone
     * does not make the complete lookup-and-dispatch operation thread-safe.
     */
    @Override
    public <E extends Event> E callEvent(E event) {
        var handlers = eventClassToHandlerMap.get(event.getClass());
        if (handlers == null || handlers.isEmpty()) {
            return event;
        }

        // Traverse the current copy-on-write array so listener changes made by
        // a callback cannot alter which remaining handlers this dispatch has already captured.
        handlers.forEach(handler -> handler.invoke(event));
        return event;
    }
}
