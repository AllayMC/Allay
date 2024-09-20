package org.allaymc.api.i18n;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.LOCAL_VARIABLE})
public @interface MayContainTrKey {
}
