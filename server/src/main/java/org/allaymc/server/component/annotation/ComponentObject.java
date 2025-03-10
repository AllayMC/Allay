package org.allaymc.server.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a field to be injected with the component owner instance during initialization.
 *
 * <p>
 * Used by the component system to inject a reference to the owning object into a component field.
 * The field must be accessible and compatible with the owner type.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentObject {
}
