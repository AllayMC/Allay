package org.allaymc.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation used to mark a method as being slow or blocking a thread
 * <p>
 * It usually means that you may need to call it asynchronously
 * <p>
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SlowOperation {
}
