package cn.allay.api.block.component.annotation;

import cn.allay.api.block.property.type.BlockPropertyType;

import java.lang.annotation.*;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/15 <br>
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
