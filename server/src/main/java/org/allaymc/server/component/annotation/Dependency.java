package org.allaymc.server.component.annotation;

import org.allaymc.server.utils.ComponentInjectException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Dependency} declares a dependency on another component for injection
 * into a field.
 * <p>
 * The annotation identifies a dependency field within a component implementation.
 * During object construction, the injector scans fields for this annotation and attempts to
 * resolve the dependency either by assignable type (if {@code identifier} is blank) or by the
 * specified {@code identifier}. The dependency must exist within the provided component list,
 * or a {@link ComponentInjectException} is thrown unless {@code optional} is {@code true}.
 * Multiple matches are rejected even for optional dependencies. Only fields
 * whose declared type implements the component interface participate in injection.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dependency {
    /**
     * Returns the identifier text used instead of assignable-type selection.
     * <p>
     * Empty or whitespace-only text selects by field type. A nonblank value
     * must exactly match a discovered identifier and still select an instance
     * assignable to the field; surrounding whitespace is not stripped.
     *
     * @return identifier text, or a blank string for selection by field type
     */
    String identifier() default "";

    /**
     * Returns whether a missing candidate is permitted during dependency injection.
     * <p>
     * When {@code true}, no candidate leaves the field's existing value unchanged.
     * The default {@code false} rejects absence; neither setting permits multiple
     * matches or an incompatible instance selected by identifier.
     *
     * @return {@code true} if the dependency is optional, {@code false} otherwise
     */
    boolean optional() default false;
}
