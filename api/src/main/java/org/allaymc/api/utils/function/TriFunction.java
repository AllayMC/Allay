package org.allaymc.api.utils.function;

/**
 * {@code TriFunction} represents a computation accepting three independently
 * typed arguments and returning a result. The four type parameters preserve the
 * input positions and result type when callers store callbacks, pass lambdas,
 * or reference methods with compatible signatures.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    /**
     * Computes a result from the three supplied argument values.
     */
    R apply(T t, U u, V v);
}
