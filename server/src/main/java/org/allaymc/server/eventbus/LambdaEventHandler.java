package org.allaymc.server.eventbus;

import org.allaymc.api.eventbus.event.Event;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public class LambdaEventHandler<E extends Event> extends AbstractEventHandler {

    protected final Consumer<E> eventConsumer;

    public LambdaEventHandler(boolean async, int priority, Class<E> eventClass, ExecutorService asyncExecutorService, Consumer<E> eventConsumer) {
        super(async, priority, eventClass, asyncExecutorService);
        this.eventConsumer = eventConsumer;
    }

    @Override
    protected void invoke0(Object event) {
        this.eventConsumer.accept((E) event);
    }
}
