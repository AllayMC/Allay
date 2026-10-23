package org.allaymc.server.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.Event;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * {@code LambdaEventHandler} invokes a consumer registered for one event class
 * and reports consumer
 * failures through the server logger. The inherited dispatch policy chooses
 * synchronous or asynchronous execution, while this adapter accepts cancelled
 * events and passes the original event instance directly to the consumer.
 *
 * @author daoge_cmd
 */
@Slf4j
public class LambdaEventHandler<E extends Event> extends AbstractEventHandler {

    /**
     * {@code eventConsumer} retains the registered consumer directly. Consumer
     * unregistration compares this reference by identity rather than by its
     * equality implementation.
     */
    protected final Consumer<E> eventConsumer;

    /**
     * Creates a consumer handler with cancellation filtering disabled.
     * The event class must agree with the consumer's accepted type, and the
     * supplied executor remains owned by the bus or its caller.
     */
    public LambdaEventHandler(boolean async, int priority, Class<E> eventClass, ExecutorService asyncExecutorService, Consumer<E> eventConsumer) {
        super(async, priority, false, eventClass, asyncExecutorService);
        this.eventConsumer = eventConsumer;
    }

    /**
     * Passes the event to the retained consumer after the inherited policy
     * checks. Any throwable from conversion or consumer execution is logged and
     * suppressed, allowing dispatch to continue to other registered handlers.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void invoke0(Object event) {
        try {
            this.eventConsumer.accept((E) event);
        } catch (Throwable t) {
            // A consumer failure must not abort delivery to later handlers;
            // record its throwable here and return normally to the bus's dispatch loop.
            log.error("An error occurred while handling event", t);
        }
    }
}
