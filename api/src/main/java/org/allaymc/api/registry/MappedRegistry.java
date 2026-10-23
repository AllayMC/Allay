package org.allaymc.api.registry;

import java.util.Map;
import java.util.function.Supplier;

/**
 * {@code MappedRegistry} exposes a loader-supplied map while preserving its
 * concrete map type. Key lookups and registrations follow the backing map's
 * rules. Inherited freezing blocks registry mutation methods but retains the
 * original map, so callers holding its reference can still modify a mutable map
 * directly.
 *
 * @author GeyserMC | daoge_cmd
 */
public class MappedRegistry<KEY, VALUE, MAPPING extends Map<KEY, VALUE>> extends AbstractMappedRegistry<KEY, VALUE, MAPPING> {
    /**
     * Loads the concrete backing map immediately from the supplied input. The
     * map is retained without copying or wrapping, preserving both its runtime
     * type and any references held by the loader.
     */
    protected <INPUT> MappedRegistry(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Creates a registry by invoking the supplied loader with {@code null}
     * input. The loader must support that convention and return the map to
     * retain; loading occurs before this factory returns.
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> AbstractMappedRegistry<KEY, VALUE, MAPPING> of(RegistryLoader<INPUT, MAPPING> registryLoader) {
        return new MappedRegistry<>(null, registryLoader);
    }

    /**
     * Creates a registry by loading its map from the supplied input. Loading
     * occurs immediately, and the returned map is retained directly without
     * copying, preserving the loader's concrete map type.
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> AbstractMappedRegistry<KEY, VALUE, MAPPING> of(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        return new MappedRegistry<>(input, registryLoader);
    }

    /**
     * Creates a registry using a loader obtained from the supplier once. The
     * loader receives {@code null} input immediately, so both supplier
     * invocation and loading finish before the registry is returned.
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> AbstractMappedRegistry<KEY, VALUE, MAPPING> of(Supplier<RegistryLoader<INPUT, MAPPING>> registryLoader) {
        return new MappedRegistry<>(null, registryLoader.get());
    }

    /**
     * Creates a registry using {@code input} and a loader obtained once from
     * {@code registryLoader}. The loader runs immediately, and its map is
     * retained directly; later lookups do not invoke either callback again.
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> AbstractMappedRegistry<KEY, VALUE, MAPPING> of(INPUT input, Supplier<RegistryLoader<INPUT, MAPPING>> registryLoader) {
        return new MappedRegistry<>(input, registryLoader.get());
    }
}
