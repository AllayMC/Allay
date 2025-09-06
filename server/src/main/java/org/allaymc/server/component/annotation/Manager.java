package org.allaymc.server.component.annotation;

import org.allaymc.server.component.interfaces.ComponentManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a field to be injected with the {@link ComponentManager} instance.
 *
 * <p>
 * Enables the component system to inject a {@link ComponentManager} reference into the annotated field,
 * allowing interaction with the component management system. The field must be accessible and compatible
 * with {@link ComponentManager} or its implementations.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Manager {
}
