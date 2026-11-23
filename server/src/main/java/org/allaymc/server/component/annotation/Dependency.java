package org.allaymc.server.component.annotation;

import org.allaymc.server.utils.ComponentInjectException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code Dependency} declares a dependency on another component for injection
 * into a field. During type blueprint compilation, the component engine resolves
 * each annotated component-typed field by assignable type against the configured
 * providers. Missing required dependencies and ambiguous multi-candidate matches
 * fail immediately during blueprint build with {@link ComponentInjectException}.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dependency {
    /**
     * Returns whether a missing candidate is permitted during dependency resolution.
     * When {@code true}, absence leaves the field at its existing value; the default
     * {@code false} rejects absence at blueprint compilation time, and neither setting
     * permits multiple assignable candidates.
     *
     * @return {@code true} if the dependency is optional, {@code false} otherwise
     */
    boolean optional() default false;
}
