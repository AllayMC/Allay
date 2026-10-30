package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * {@code CallerThread} documents the execution contexts expected by a type or
 * method. A declaration can identify where non-thread-safe code must be used or
 * which server thread normally invokes a method or event.
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
     * Returns the execution context documented for the annotated declaration.
     *
     * @return the {@link ThreadType}
     */
    ThreadType value();

    /**
     * {@code Container} groups repeated thread-context annotations attached to
     * one type or method.
     */
    @Documented
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    @interface Container {
        /**
         * Returns the thread-context annotations grouped on this annotated declaration.
         */
        CallerThread[] value();
    }
}
