package cn.allay.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * Describes an injectable point in the interface to be injected<br/>
 * Injection succeeds when the injection point (a method) is marked by {@link Inject} and the provided implementation method is marked by the {@link Impl} annotation <br/>
 * We do this to prevent unexpected injections from occurring
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Inject {
}
