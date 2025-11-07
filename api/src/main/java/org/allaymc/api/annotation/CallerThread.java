package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * CallerThread annotation is used to indicate in which threaded environment non-thread-safe classes
 * and methods should be used. It is also used to indicate in what thread environment a method or
 * event will be called.
 *
 * @author daoge_cmd
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface CallerThread {
    /**
     * Returns the thread type.
     *
     * @return the {@link ThreadType}
     */
    ThreadType value();
}
