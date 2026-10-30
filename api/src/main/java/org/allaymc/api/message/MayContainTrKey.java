package org.allaymc.api.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code MayContainTrKey} marks values that may contain translation keys for developer and tooling
 * guidance. The annotation is retained in class files on supported declarations and local
 * variables; it performs no lookup, validation, or automatic translation itself.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT, ElementType.LOCAL_VARIABLE})
public @interface MayContainTrKey {
}
