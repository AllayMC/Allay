package org.allaymc.api.block.component.annotation;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.utils.exception.BlockComponentInjectException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is an annotation for validation, used on {@link org.allaymc.api.block.component.BlockComponent BlockComponent} and its subclasses,
 * to validate whether the {@link org.allaymc.api.block.type.BlockType BlockType} carrying this annotation has defined specified BlockProperty.
 * <p>
 * If validation fails due to non-existent or mismatched BlockProperty, it will throw a {@link BlockComponentInjectException BlockComponentInjectException}.
 * <p>
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Repeatable(RequireBlockProperty.Requirements.class)
public @interface RequireBlockProperty {
    /**
     * The types of BlockProperty can be BOOLEAN, INT, ENUM.
     */
    BlockPropertyType.Type type();

    /**
     * The name of BlockProperty
     */
    String name();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @interface Requirements {
        RequireBlockProperty[] value();
    }
}
