package org.allaymc.api.utils.function;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void apply(T t, U u, V v);
}
