package org.allaymc.server.eventbus;

import lombok.extern.slf4j.Slf4j;
import me.sunlan.fastreflection.FastMemberLoader;
import me.sunlan.fastreflection.FastMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * {@code MethodEventHandler} invokes one annotated listener method through a
 * prepared fast-reflection handle. The handler retains the listener instance,
 * while class-loader-specific
 * member loaders are shared between handles. Inherited dispatch settings select
 * execution and cancellation filtering; failures inside reflective invocation
 * are logged without aborting delivery to subsequent handlers.
 *
 * @author daoge_cmd
 */
@Slf4j
public class MethodEventHandler extends AbstractEventHandler {

    /**
     * {@code FAST_MEMBER_LOADERS} shares fast-reflection member loaders by
     * listener class loader. Entries retain those loaders after listeners are
     * unregistered.
     */
    protected static final Map<ClassLoader, FastMemberLoader> FAST_MEMBER_LOADERS = new ConcurrentHashMap<>();

    /** Prepared listener method handle. */
    protected final FastMethod method;
    /** Registered listener invocation target. */
    protected final Object instance;

    /**
     * Creates a method handler and prepares its reflective invocation handle.
     * The listener's class loader selects a shared member loader; callers supply
     * an already validated method accepting the indexed event class and returning
     * {@code void}. Handle preparation failures propagate during listener registration.
     */
    public MethodEventHandler(
            boolean async,
            int priority,
            boolean ignoreCancelled,
            Class<?> eventClass,
            ExecutorService asyncExecutorService,
            Method method,
            Object instance
    ) {
        super(async, priority, ignoreCancelled, eventClass, asyncExecutorService);
        this.method = FastMethod.create(
                method,
                FAST_MEMBER_LOADERS.computeIfAbsent(
                        instance.getClass().getClassLoader(),
                        FastMemberLoader::new
                ),
                true
        );
        this.instance = instance;
    }

    /**
     * Invokes the prepared method on the retained listener instance.
     * Any throwable from reflection or the listener is logged and suppressed,
     * allowing the event bus to continue dispatching other handlers.
     */
    @Override
    protected void invoke0(Object event) {
        try {
            method.invoke(instance, event);
        } catch (Throwable t) {
            // Listener exceptions belong to this handler invocation; logging and
            // returning keeps one failing method from preventing delivery to the remaining listeners.
            log.error("An error occurred while handling event", t);
        }
    }
}
