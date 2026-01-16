package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * CallerThread annotation is used to indicate in which threaded environment non-thread-safe classes
 * and methods should be used. It is also used to indicate in what thread environment a method or
 * event will be called.
 * <p>
 * This annotation is repeatable, meaning multiple {@code @CallerThread} annotations can be applied
 * to the same element to indicate that it can be called from multiple thread types.
 * <p>
 * <b>Note:</b> When this annotation is applied to an event class, it only indicates the thread type
 * used by the core server code. Plugins may call the same events from different threads, but this
 * annotation does not account for plugin behavior.
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
