/*
 * Copyright (c) 2005 Brian Goetz
 * Released under the Creative Commons Attribution License
 *   (http://creativecommons.org/licenses/by/2.5)
 * Official home: http://www.jcip.net
 */
package org.allaymc.api.annotation;

import java.lang.annotation.*;

/**
 * {@code ThreadSafe} documents that an annotated type supports concurrent
 * access within its stated contract. The annotation records that claim in
 * class files without adding synchronization; implementations must provide
 * the ordering and state protection their public operations require without
 * additional caller-side coordination.
 *
 * @see NotThreadSafe
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ThreadSafe {
}
