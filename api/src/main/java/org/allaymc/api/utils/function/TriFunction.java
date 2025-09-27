package org.allaymc.api.utils.function;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
