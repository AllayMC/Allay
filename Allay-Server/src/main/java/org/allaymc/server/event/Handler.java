package org.allaymc.server.event;

import me.sunlan.fastreflection.FastMethod;
import org.allaymc.api.event.EventException;
import org.allaymc.api.server.Server;

import java.lang.reflect.Method;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class Handler {
    protected final FastMethod method;
    protected final Object instance;
    protected final boolean async;

    protected Handler(Method method, Object instance, boolean async) {
        this.method = FastMethod.create(method, true);
        this.instance = instance;
        this.async = async;
    }

    public static Handler wrap(Method method, Object instance, boolean async) {
        return new Handler(method, instance, async);
    }

    public void invoke(Object event) {
        if (!async) {
            invoke0(event);
        } else {
            Server.getInstance().getVirtualThreadPool().submit(() -> {
                invoke0(event);
            });
        }
    }

    protected void invoke0(Object event) {
        try {
            method.invoke(instance, event);
        } catch (Throwable e) {
            throw new EventException(e);
        }
    }
}
