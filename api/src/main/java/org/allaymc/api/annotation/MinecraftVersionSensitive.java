package org.allaymc.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated element may change with updates to Minecraft versions.
 * <p>
 * This annotation is used to mark classes, fields, or packages that are subject to modification
 * when updating to a new Minecraft version. While these elements are included in the API, they
 * are not considered part of the stable public API. They are provided to be used by developers
 * who are willing and able to update their plugins promptly during version transitions.
 * <p>
 * Developers using elements marked with this annotation should be aware that they may change
 * in future updates, requiring corresponding adjustments to their code.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PACKAGE})
public @interface MinecraftVersionSensitive {
}
