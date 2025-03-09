package org.allaymc.server.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a method to be invoked after component initialization completes.
 *
 * <p>
 * Methods with this annotation are called post-initialization with a single
 * {@link org.allaymc.api.component.interfaces.ComponentInitInfo} parameter. Must be public, return void,
 * and take exactly one parameter. Useful for setup or validation after dependency injection.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnInitFinish {
}
