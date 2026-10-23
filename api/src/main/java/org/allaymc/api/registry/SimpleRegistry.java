package org.allaymc.api.registry;

import java.util.function.Supplier;

/**
 * {@code SimpleRegistry} holds a loader result without imposing a map
 * representation. Factories load content immediately and retain the result in
 * the inherited registry state. Freezing prevents later registry mutation calls
 * but does not copy the content or prevent direct changes to a mutable result.
 *
 * @author GeyserMC | daoge_cmd
 */
public class SimpleRegistry<CONTENT> extends AbstractRegistry<CONTENT> {
    /**
     * {@code content} is a separate subclass slot unused by inherited
     * operations. Loading, reading, and replacement operate on
     * {@link AbstractRegistry#content} instead; this slot remains {@code null}
     * unless a subclass writes it.
     */
    protected CONTENT content;

    /**
     * Loads registry content immediately from the supplied input. The result is
     * stored in the superclass without copying, so its ownership and mutability
     * follow the loader's returned object.
     */
    protected <INPUT> SimpleRegistry(INPUT input, RegistryLoader<INPUT, CONTENT> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Creates a registry using a loader obtained from the supplier once. The
     * loader receives {@code null} input immediately, so both supplier
     * invocation and loading finish before the registry is returned.
     */
    public static <INPUT, CONTENT> SimpleRegistry<CONTENT> create(Supplier<RegistryLoader<INPUT, CONTENT>> registryLoader) {
        return new SimpleRegistry<>(null, registryLoader.get());
    }

    /**
     * Creates a registry using the supplied input and a loader obtained once.
     * The loader runs immediately, and its result is retained directly; neither
     * the supplier nor loader is invoked by later reads.
     */
    public static <INPUT, CONTENT> SimpleRegistry<CONTENT> create(INPUT input, Supplier<RegistryLoader<INPUT, CONTENT>> registryLoader) {
        return new SimpleRegistry<>(input, registryLoader.get());
    }

    /**
     * Creates a registry by invoking the supplied loader with {@code null}
     * input. The loader must support that convention and return the content to
     * retain; loading occurs before this factory returns.
     */
    public static <INPUT, CONTENT> SimpleRegistry<CONTENT> create(RegistryLoader<INPUT, CONTENT> registryLoader) {
        return new SimpleRegistry<>(null, registryLoader);
    }

    /**
     * Creates a registry by loading its content from the supplied input.
     * Loading occurs immediately, and the returned object is retained directly
     * without copying or applying additional {@code null} validation.
     */
    public static <INPUT, CONTENT> SimpleRegistry<CONTENT> create(INPUT input, RegistryLoader<INPUT, CONTENT> registryLoader) {
        return new SimpleRegistry<>(input, registryLoader);
    }
}
