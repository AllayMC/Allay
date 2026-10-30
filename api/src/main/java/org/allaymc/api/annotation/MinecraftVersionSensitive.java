package org.allaymc.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code MinecraftVersionSensitive} marks API declarations that may change
 * during Minecraft version transitions. The annotation applies to types, fields,
 * and packages; those declarations are outside the stable API contract, so
 * plugin authors using them must accommodate changes when Minecraft support advances.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PACKAGE})
public @interface MinecraftVersionSensitive {
}
