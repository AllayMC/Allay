package org.allaymc.api.utils.function;

/**
 * {@code QuadConsumer} represents an operation accepting four independently
 * typed arguments for its side effects. The type parameters preserve each
 * argument's position when callers register lambdas or compatible method
 * references; the callback implementation determines the effects of consuming
 * those values.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface QuadConsumer<T, U, V, W> {
    /**
     * Performs this callback's operation using the four supplied arguments.
     */
    void accept(T t, U u, V v, W w);
}
