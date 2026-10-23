package org.allaymc.server.eventbus;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

import java.util.concurrent.ExecutorService;

/**
 * {@code AbstractEventHandler} controls when one registered handler receives a
 * dispatched event.
 * Registration fixes its event class, priority, execution mode, and cancellation
 * policy; subclasses supply the actual consumer or method invocation. The
 * generated constructor stores these settings and the borrowed executor without
 * creating a separate executor for the handler.
 *
 * <p>
 * Asynchronous submission separates dispatch from invocation, so cancellation
 * can change between those operations. A handler that ignores cancelled events
 * checks before submission and again when the executor runs it. Both checks
 * inspect the same event instance without adding synchronization to its mutable
 * state. This narrows the cancellation window but is not an atomic guarantee
 * against concurrent cancellation after the final check.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public abstract class AbstractEventHandler {

    /** Asynchronous executor submission flag. */
    protected final boolean async;
    /**
     * {@code priority} determines dispatch position. The bus visits larger values
     * before smaller values when ordering registered handlers for an event.
     */
    protected final int priority;
    /**
     * {@code ignoreCancelled} enables cancellation checks before dispatch and
     * asynchronous invocation. Each check reads the event's current cancellation flag.
     */
    protected final boolean ignoreCancelled;
    /** Exact indexed event class. */
    protected final Class<?> eventClass;
    /**
     * {@code asyncExecutorService} supplies the borrowed executor for asynchronous
     * invocation. This handler neither closes it nor waits for submitted tasks
     * to finish.
     */
    protected final ExecutorService asyncExecutorService;

    /**
     * Invokes or submits this handler unless its cancellation policy suppresses
     * the supplied event. Synchronous execution checks immediately before
     * invocation; asynchronous execution checks both before submission and
     * inside the submitted task. A cancellation occurring after submission can
     * therefore suppress the callback when the later check observes it.
     *
     * <p>
     * The event reference is shared rather than copied. These checks do not
     * synchronize its state, and cancellation can still change after a check.
     * Executor rejection propagates directly to the dispatching caller.
     */
    public void invoke(Object event) {
        if (!async) {
            if (shouldIgnore(event)) {
                return;
            }

            invoke0(event);
        } else {
            if (shouldIgnore(event)) {
                return;
            }

            // Cancellation can change while this task waits in the executor;
            // recheck at invocation so an observed later cancellation can still suppress the handler.
            asyncExecutorService.submit(() -> {
                if (shouldIgnore(event)) {
                    return;
                }

                invoke0(event);
            });
        }
    }

    /**
     * Returns whether this handler's policy rejects the event's current
     * cancellation state. With filtering disabled, the short-circuit result is
     * {@code false}; with filtering enabled, callers must supply an {@link Event} instance.
     */
    protected boolean shouldIgnore(Object event) {
        return ignoreCancelled && ((Event) event).isCancelled();
    }

    /**
     * Performs the subclass-specific callback after dispatch policy checks finish.
     */
    protected abstract void invoke0(Object event);
}
