package org.allaymc.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allay Project 2024/7/25
 *
 * @author Dhaiven
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogHandler {
    PluginLoadOrder order() default PluginLoadOrder.START_UP;
}
