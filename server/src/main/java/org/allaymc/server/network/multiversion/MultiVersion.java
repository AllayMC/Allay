package org.allaymc.server.network.multiversion;

import java.lang.annotation.*;

/**
 * Notes MultiVersion is used to mark a class or method that contains multi-version related code, which
 * usually means that these classes or methods need extra attention when making protocol updates. This
 * annotation also makes it easy for developers to quickly jump to where an update is needed when removing
 * support for older versions.
 *
 * @author daoge_cmd
 */
@Documented
@Repeatable(MultiVersion.Container.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface MultiVersion {

    /**
     * Game version(s) that the changes are related to.
     */
    String version() default "";

    /**
     * Extra details about the changes.
     */
    String details() default "";

    @Documented
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    @interface Container {
        MultiVersion[] value();
    }
}
