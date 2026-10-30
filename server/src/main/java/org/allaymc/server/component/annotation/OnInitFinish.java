package org.allaymc.server.component.annotation;

import org.allaymc.api.component.ComponentInitInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code OnInitFinish} marks a method for invocation after component
 * initialization completes.
 *
 * <p>
 * Participating methods must be public, return {@code void}, and accept one parameter
 * compatible with the supplied {@link ComponentInitInfo} instance; inherited
 * public methods are included. Other method shapes are skipped. All injections
 * and listener registrations finish first, but the owner's subclass constructor
 * has not yet completed, and hook methods have no explicitly sorted order.
 * </p>
 *
 * @author daoge_cmd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnInitFinish {
}
