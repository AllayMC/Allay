package org.allaymc.server.eventbus;

import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutorService;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public abstract class AbstractEventHandler {

    protected final boolean async;
    protected final int priority;
    protected final Class<?> eventClass;
    protected final ExecutorService asyncExecutorService;

    public void invoke(Object event) {
        if (!async) {
            invoke0(event);
        } else {
            asyncExecutorService.submit(() -> invoke0(event));
        }
    }

    protected abstract void invoke0(Object event);
}
