package org.allaymc.server.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code ComponentObject} marks a field for injection with the component owner
 * instance during initialization.
 *
 * <p>
 * The injector scans declared fields through the component's superclass chain
 * and makes annotated fields accessible, so private fields can participate.
 * Their types must accept the owner instance. Owner references are assigned
 * after managers and event listeners are installed, before completion hooks run.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ComponentObject {
}
