package org.allaymc.api.i18n;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark the text which may contains translation key.
 * Usually used in method parameters to inform the callers that they can use
 * translation key in these parameters.
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.LOCAL_VARIABLE})
public @interface MayContainTrKey {
}
