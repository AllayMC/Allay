package org.allaymc.server.eventbus;

import me.sunlan.fastreflection.FastMemberLoader;
import me.sunlan.fastreflection.FastMethod;
import org.allaymc.api.eventbus.EventException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class Handler {
    protected static final Map<ClassLoader, FastMemberLoader> FAST_MEMBER_LOADERS = new ConcurrentHashMap<>();
    protected final FastMethod method;
    protected final Object instance;
    protected final boolean async;
    protected final int priority;
    protected final Class<?> eventClass;
    protected final ExecutorService asyncExecutorService;

    public Handler(Method method, Object instance, boolean async, int priority, Class<?> eventClass, ExecutorService asyncExecutorService) {
        this.method = FastMethod.create(
                method,
                FAST_MEMBER_LOADERS.computeIfAbsent(
                        instance.getClass().getClassLoader(),
                        FastMemberLoader::new),
                true
        );
        this.instance = instance;
        this.async = async;
        this.priority = priority;
        this.eventClass = eventClass;
        this.asyncExecutorService = asyncExecutorService;
    }

    public void invoke(Object event) {
        if (!async) {
            invoke0(event);
        } else {
            asyncExecutorService.submit(() -> {
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
