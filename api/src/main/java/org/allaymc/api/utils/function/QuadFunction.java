package org.allaymc.api.utils.function;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}
