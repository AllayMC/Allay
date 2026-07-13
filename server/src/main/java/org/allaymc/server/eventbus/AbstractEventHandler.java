package org.allaymc.server.eventbus;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

import java.util.concurrent.ExecutorService;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public abstract class AbstractEventHandler {

    protected final boolean async;
    protected final int priority;
    protected final boolean ignoreCancelled;
    protected final Class<?> eventClass;
    protected final ExecutorService asyncExecutorService;

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

            asyncExecutorService.submit(() -> {
                if (shouldIgnore(event)) {
                    return;
                }

                invoke0(event);
            });
        }
    }

    protected boolean shouldIgnore(Object event) {
        return ignoreCancelled && ((Event) event).isCancelled();
    }

    protected abstract void invoke0(Object event);
}
