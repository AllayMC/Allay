package org.allaymc.server.eventbus;

import me.sunlan.fastreflection.FastMemberLoader;
import me.sunlan.fastreflection.FastMethod;
import org.allaymc.api.eventbus.EventException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * @author daoge_cmd
 */
public class MethodEventHandler extends AbstractEventHandler {

    protected static final Map<ClassLoader, FastMemberLoader> FAST_MEMBER_LOADERS = new ConcurrentHashMap<>();

    protected final FastMethod method;
    protected final Object instance;

    public MethodEventHandler(boolean async, int priority, Class<?> eventClass, ExecutorService asyncExecutorService, Method method, Object instance) {
        super(async, priority, eventClass, asyncExecutorService);
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

    @Override
    protected void invoke0(Object event) {
        try {
            method.invoke(instance, event);
        } catch (Throwable e) {
            throw new EventException(e);
        }
    }
}
