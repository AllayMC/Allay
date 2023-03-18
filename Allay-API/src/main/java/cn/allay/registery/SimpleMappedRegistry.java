package cn.allay.registery;

import java.util.Map;
import java.util.function.Supplier;

/**
 * A public registry holding a map of various registrations as defined by {@link MAPPING}.
 * The M represents the map class, which can be anything that extends {@link Map}. The
 * {@link KEY} and {@link VALUE} generics are the key and value respectively.
 *
 * @param <KEY> the key
 * @param <VALUE> the value
 * @param <MAPPING> the map
 * <p>
 * Author: GeyserMC & daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 */
public class SimpleMappedRegistry<KEY, VALUE, MAPPING extends Map<KEY, VALUE>> implements MappedRegistry<KEY, VALUE, MAPPING> {

    protected MAPPING mappings;

    protected <INPUT> SimpleMappedRegistry(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        this.mappings = registryLoader.load(input);
    }

    @Override
    public MAPPING getContent() {
        return mappings;
    }

    @Override
    public void setContent(MAPPING mappings) {
        this.mappings = mappings;
    }

    /**
     * Creates a new mapped registry with the given {@link RegistryLoader}. The
     * input type is not specified here, meaning the loader return type is either
     * predefined, or the registry is populated at a later point.
     *
     * @param registryLoader the registry loader
     * @param <INPUT> the input
     * @param <KEY> the map key
     * @param <VALUE> the map value
     * @param <MAPPING> the returned mappings type, a map in this case
     * @return a new registry with the given RegistryLoader
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> MappedRegistry<KEY, VALUE, MAPPING> create(RegistryLoader<INPUT, MAPPING> registryLoader) {
        return new SimpleMappedRegistry<>(null, registryLoader);
    }

    /**
     * Creates a new mapped registry with the given {@link RegistryLoader} and input.
     *
     * @param input the input
     * @param registryLoader the registry loader
     * @param <INPUT> the input
     * @param <KEY> the map key
     * @param <VALUE> the map value
     * @param <MAPPING> the returned mappings type, a map in this case
     * @return a new registry with the given RegistryLoader
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> MappedRegistry<KEY, VALUE, MAPPING> create(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        return new SimpleMappedRegistry<>(input, registryLoader);
    }

    /**
     * Creates a new mapped registry with the given {@link RegistryLoader} supplier.
     * The input type is not specified here, meaning the loader return type is either
     * predefined, or the registry is populated at a later point.
     *
     * @param registryLoader the registry loader supplier
     * @param <INPUT> the input
     * @param <KEY> the map key
     * @param <VALUE> the map value
     * @param <MAPPING> the returned mappings type, a map in this case
     * @return a new registry with the given RegistryLoader supplier
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> MappedRegistry<KEY, VALUE, MAPPING> create(Supplier<RegistryLoader<INPUT, MAPPING>> registryLoader) {
        return new SimpleMappedRegistry<>(null, registryLoader.get());
    }

    /**
     * Creates a new mapped registry with the given {@link RegistryLoader} and input.
     *
     * @param registryLoader the registry loader
     * @param <INPUT> the input
     * @param <KEY> the map key
     * @param <VALUE> the map value
     * @param <MAPPING> the returned mappings type, a map in this case
     * @return a new registry with the given RegistryLoader supplier
     */
    public static <INPUT, KEY, VALUE, MAPPING extends Map<KEY, VALUE>> MappedRegistry<KEY, VALUE, MAPPING> create(INPUT input, Supplier<RegistryLoader<INPUT, MAPPING>> registryLoader) {
        return new SimpleMappedRegistry<>(input, registryLoader.get());
    }
}
