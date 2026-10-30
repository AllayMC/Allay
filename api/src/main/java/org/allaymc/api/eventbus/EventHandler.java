package org.allaymc.api.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code EventHandler} marks a method for discovery when its containing listener
 * is registered.
 * A handler must return {@code void} and accept one parameter derived from the event
 * base class. Its parameter selects an exact event class; these options govern
 * dispatch mode, priority, and whether cancellation suppresses the invocation.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Returns whether dispatch submits this handler to the bus's executor.
     * The default is synchronous execution on the dispatching thread; asynchronous
     * execution shares the event object and is not awaited by the event call.
     *
     * @return {@code true} for executor submission, otherwise {@code false}
     */
    boolean async() default false;

    /**
     * Returns the handler's dispatch priority, with larger values visited first.
     * The default is zero. Priority orders asynchronous submissions along with
     * synchronous calls, but does not establish asynchronous start or completion order.
     *
     * @return the priority of the event handler
     */
    int priority() default 0;

    /**
     * Returns whether cancellation should suppress this handler's invocation.
     * The default is {@code false}. The server implementation checks this condition
     * before asynchronous submission and again inside the submitted task, using
     * the shared event's cancellation state at each check.
     *
     * @return {@code true} to suppress invocation for cancelled events, otherwise
     *         {@code false}
     */
    boolean ignoreCancelled() default false;
}
