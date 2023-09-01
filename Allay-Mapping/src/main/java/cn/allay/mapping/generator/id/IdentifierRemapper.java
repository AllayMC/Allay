package cn.allay.mapping.generator.id;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface IdentifierRemapper {
    /**
     * Regex string to check for when remapping states for blocks. Leave
     * empty to check against all blocks.
     *
     * @return regex string to check against when remapping states for blocks
     */
    String[] blockRegex() default "";
}
