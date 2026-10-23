package org.allaymc.api.utils.function;

/**
 * {@code TriConsumer} represents an operation accepting three independently
 * typed arguments for its side effects. The type parameters preserve each
 * argument's position when callers register lambdas or compatible method
 * references; the callback implementation determines the effects of consuming
 * those values.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {
    /**
     * Performs this callback's operation using the three supplied arguments.
     */
    void apply(T t, U u, V v);
}
