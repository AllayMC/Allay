/*
 * Copyright (c) 2005 Brian Goetz
 * Released under the Creative Commons Attribution License
 *   (http://creativecommons.org/licenses/by/2.5)
 * Official home: http://www.jcip.net
 */
package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * {@code NotThreadSafe} documents that an annotated type requires caller-side
 * coordination for concurrent use. The annotation is retained in class files
 * for documentation and analysis; it neither adds synchronization nor changes
 * runtime behavior. Each annotated type defines the particular restrictions
 * its callers must obey.
 *
 * @see ThreadSafe
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface NotThreadSafe {
}
