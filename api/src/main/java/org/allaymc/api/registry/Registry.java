package org.allaymc.api.registry;

import java.util.function.Consumer;

/**
 * {@code Registry} exposes loaded content and operations for populating or replacing that
 * content. Implementations define its representation, ownership, and mutation
 * policy. Freezing is optional: the default methods retain compatibility with
 * implementations that remain mutable, while built-in registries can reject
 * subsequent mutations through their registry methods.
 *
 * <p>
 * A {@link RegistryLoader} can obtain content from an input such as a file path
 * or directly transform a supplied value. Content access does not by itself
 * imply a defensive copy, deep immutability, or concurrent mutation support.
 * Callers must follow the concrete registry's rules before retaining or
 * changing its content.
 *
 * <p>
 * Registries can be very simple to create. Here is an example that simply parses a
 * number given a string:
 *
 * <pre>{@code public static final SimpleRegistry<Integer> STRING_TO_INT = SimpleRegistry.create("5", Integer::parseInt);}</pre>
 *
 * <p>
 * The loader runs when this registry is created; later reads retrieve its
 * stored result rather than repeating the conversion.
 *
 * @param <CONTENT> the type of the value which is being held by the registry
 *
 * @author GeyserMC | daoge_cmd
 */
public interface Registry<CONTENT> {
    /**
     * Passes the stored content to a consumer for registration changes.
     * Implementations supporting freezing may reject the call after freezing;
     * callback failures need not roll back changes already applied to content.
     */
    void register(Consumer<CONTENT> consumer);

    /**
     * Returns the content currently held by this registry. The result can share
     * mutable state with the registry, and freezing does not universally imply
     * an immutable result. Callers must follow the implementation's ownership
     * rules before modifying it.
     */
    CONTENT getContent();

    /**
     * Replaces the content used by subsequent registry operations.
     * Implementations define whether replacement is allowed after freezing;
     * references obtained before replacement can still refer to the previous
     * value, which need not be cleared or closed.
     */
    void setContent(CONTENT content);

    /**
     * Requests that this registry reject subsequent mutation through its
     * methods.
     *
     * <p>
     * The default implementation does nothing, so existing custom registries
     * remain mutable unless they opt into freezing. Callers must consult the
     * concrete implementation for guarantees about exposed content references.
     */
    default void freeze() {
    }

    /**
     * Returns whether the implementation reports that registry mutations are
     * frozen.
     *
     * <p>
     * The default returns {@code false}, matching the default no-op
     * {@code freeze} operation. Implementations supporting freezing override
     * this method with their state.
     *
     * @return {@code true} if mutations are no longer accepted
     */
    default boolean isFrozen() {
        return false;
    }
}
