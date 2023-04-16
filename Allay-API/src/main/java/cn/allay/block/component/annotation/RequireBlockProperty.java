package cn.allay.block.component.annotation;

import cn.allay.block.property.type.BlockPropertyType;

import java.lang.annotation.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
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
