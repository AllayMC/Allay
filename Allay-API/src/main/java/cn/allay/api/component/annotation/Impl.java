package cn.allay.api.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * This annotation is used to mark a method as an injectable implementation <br/>
 * Injection succeeds when the injection point (a method) is marked by {@link Inject} and the provided implementation method is marked by the {@link Impl} annotation <br/>
 * We do this to prevent unexpected injections from occurring
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Impl {
}
