package org.allaymc.api.utils.function;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface QuadConsumer<T, U, V, W> {
    void accept(T t, U u, V v, W w);
}
