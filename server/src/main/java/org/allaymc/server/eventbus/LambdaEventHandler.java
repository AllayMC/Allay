package org.allaymc.server.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.Event;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Slf4j
public class LambdaEventHandler<E extends Event> extends AbstractEventHandler {

    protected final Consumer<E> eventConsumer;

    public LambdaEventHandler(boolean async, int priority, Class<E> eventClass, ExecutorService asyncExecutorService, Consumer<E> eventConsumer) {
        super(async, priority, eventClass, asyncExecutorService);
        this.eventConsumer = eventConsumer;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void invoke0(Object event) {
        try {
            this.eventConsumer.accept((E) event);
        } catch (Throwable t) {
            log.error("An error occurred while handling event", t);
        }
    }
}
