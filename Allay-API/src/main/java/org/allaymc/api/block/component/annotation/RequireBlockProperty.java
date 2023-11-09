package org.allaymc.api.block.component.annotation;

import org.allaymc.api.block.property.type.BlockPropertyType;

import java.lang.annotation.*;

/**
 * Allay Project 2023/4/15
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Repeatable(RequireBlockProperty.Requirements.class)
public @interface RequireBlockProperty {
    BlockPropertyType.Type type();

    String name();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    @interface Requirements {
        RequireBlockProperty[] value();
    }
}
