package org.allaymc.api.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a method as an event handler.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Whether the event handler should be called asynchronously.
     *
     * @return {@code true} if the event handler should be called asynchronously, otherwise {@code false}
     */
    boolean async() default false;

    /**
     * The priority of the event handler. Event handler with the bigger priority will be called first.
     *
     * @return the priority of the event handler
     */
    int priority() default 0;
}
