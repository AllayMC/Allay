package org.allaymc.server.component.annotation;

import org.allaymc.server.component.ComponentManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Manager} marks a field for injection with its owner's
 * {@link ComponentManager} instance.
 *
 * <p>
 * The injector makes annotated fields accessible through the component's class
 * hierarchy and assigns the owner's shared manager. Field types must accept
 * that manager implementation. Assignment precedes listener registration for
 * the component, and all such registrations finish before completion hooks run.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Manager {
}
