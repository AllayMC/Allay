package org.allaymc.api.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code GenerateImpl} instructs the Allay component annotation processor to
 * generate a concrete {@code <InterfaceName>Impl} delegation class in the same
 * package at compile time. Plugins defining custom composed interfaces for
 * blocks, items, entities, or block entities apply this annotation so every
 * component contract inherited by the interface is wired and forwarded through
 * plain Java methods without handwritten boilerplate or Lombok delegation.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface GenerateImpl {

    /**
     * Returns the fully qualified superclass name for the generated
     * implementation, or an empty string to infer the default server base class
     * from {@code Entity}, {@code ItemStack}, {@code BlockBehavior}, or
     * {@code BlockEntity}.
     *
     * @return explicit superclass binary name, or {@code ""} for automatic inference
     */
    String baseClass() default "";
}
