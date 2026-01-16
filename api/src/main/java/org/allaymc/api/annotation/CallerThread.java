package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * CallerThread annotation is used to indicate in which threaded environment non-thread-safe classes
 * and methods should be used. It is also used to indicate in what thread environment a method or
 * event will be called.
 * <p>
 * This annotation is repeatable, meaning multiple {@code @CallerThread} annotations can be applied
 * to the same element to indicate that it can be called from multiple thread types.
 *
 * @author daoge_cmd
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Repeatable(CallerThread.Container.class)
public @interface CallerThread {
    /**
     * Returns the thread type.
     *
     * @return the {@link ThreadType}
     */
    ThreadType value();

    /**
     * Container annotation for repeatable {@link CallerThread} annotations.
     */
    @Documented
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    @interface Container {
        CallerThread[] value();
    }
}
