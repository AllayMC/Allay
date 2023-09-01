package cn.allay.mapping.generator.state;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface StateRemapper {

    /**
     * Regex string to check for when remapping states for blocks. Leave
     * empty to check against all blocks.
     *
     * @return regex string to check against when remapping states for blocks
     */
    String[] blockRegex() default "";

    /**
     * The name of the Minecraft: Java Edition blockstate data.
     *
     * @return the name of the Minecraft: java edition blockstate data.
     */
    String value();
}
