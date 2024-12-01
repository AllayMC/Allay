package org.allaymc.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MinecraftVersionSensitive annotation is used to mark classes or classes under certain package
 * that may be changed when updating to the next minecraft version.
 * <p>
 * Although classes marked with this annotation are present in api, they are not part
 * of the public api. However, we decided to keep them in the api to make the developers
 * who believe that they are able to update their plugins during version updating timely
 * to develop plugins easier.
 * <p>
 * If you decided to use these classes, you should be aware
 * that they may be changed in the future, and you need to update your code accordingly.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PACKAGE})
public @interface MinecraftVersionSensitive {
}
