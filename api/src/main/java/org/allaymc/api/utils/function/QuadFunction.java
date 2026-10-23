package org.allaymc.api.utils.function;

/**
 * {@code QuadFunction} represents a computation accepting four independently
 * typed arguments and returning a result. The five type parameters preserve the
 * input positions and result type when callers store callbacks, pass lambdas,
 * or reference methods with compatible signatures.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface QuadFunction<T, U, V, W, R> {
    /**
     * Computes a result from the four supplied argument values.
     */
    R apply(T t, U u, V v, W w);
}
