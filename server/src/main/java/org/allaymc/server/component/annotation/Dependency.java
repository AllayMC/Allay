package org.allaymc.server.component.annotation;

import org.allaymc.server.utils.ComponentInjectException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares a dependency on another component for injection into a field.
 * <p>
 * Marks a field in a component implementation class to indicate a required dependency.
 * During object construction, the injector scans fields for this annotation and attempts to
 * resolve the dependency either by inheritance (if {@code identifier} is empty) or by the
 * specified {@code identifier}. The dependency must exist within the provided component list,
 * or a {@link ComponentInjectException} is thrown unless {@code optional} is {@code true}.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dependency {
    /**
     * Specifies the identifier of the required component.
     * <p>
     * If left empty, the dependency is resolved by inheritance.
     * Otherwise, it is matched against the component's explicit identifier.
     *
     * @return the component identifier, or an empty string for inheritance-based resolution
     */
    String identifier() default "";

    /**
     * Indicates whether the dependency is optional.
     * <p>
     * If {@code true}, the injector does not throw an exception if the dependency is missing.
     * Otherwise, a {@link ComponentInjectException} is thrown when the dependency is not found.
     *
     * @return {@code true} if the dependency is optional, {@code false} otherwise
     */
    boolean optional() default false;
}
