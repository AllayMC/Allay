package org.allaymc.api.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an element to signify that it may contain a translation key.
 * <p>
 * The annotated element (such as a method, field, parameter, record component,
 * or local variable) is expected to potentially contain a translation key used
 * for internationalization (I18n). This serves as a hint to developers that
 * the value might require lookup and processing for translation purposes.
 * <p>
 * This annotation is retained at the class level and can be applied to the
 * following program elements:
 * <ul>
 *   <li>Methods</li>
 *   <li>Fields</li>
 *   <li>Parameters</li>
 *   <li>Record components</li>
 *   <li>Local variables</li>
 * </ul>
 * <p>
 * The annotation itself does not enforce any checks or behavior but serves
 * as a marker to communicate developer intent to tools and other developers.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.LOCAL_VARIABLE})
public @interface MayContainTrKey {
}
