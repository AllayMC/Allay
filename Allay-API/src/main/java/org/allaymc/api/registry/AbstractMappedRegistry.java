package org.allaymc.api.registry;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * An abstract registry holding a map of various registrations as defined by {@link MAPPING}.
 * The M represents the map class, which can be anything that extends {@link Map}. The
 * {@link KEY} and {@link VALUE} generics are the key and value respectively.
 *
 * @param <KEY>     the key
 * @param <VALUE>   the value
 * @param <MAPPING> the map
 *                  <p>
 *
 * @author daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 */
public abstract class AbstractMappedRegistry<KEY, VALUE, MAPPING extends Map<KEY, VALUE>> extends AbstractRegistry<MAPPING> {
    protected <INPUT> AbstractMappedRegistry(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Returns the value registered by the given key.
     *
     * @param key the key
     *
     * @return the value registered by the given key.
     */
    public VALUE get(KEY key) {
        return content.get(key);
    }

    /**
     * Returns and maps the value by the given key if present.
     *
     * @param key    the key
     * @param mapper the mapper
     * @param <U>    the type
     *
     * @return the mapped value from the given key if present
     */
    public <U> Optional<U> map(KEY key, Function<? super VALUE, ? extends U> mapper) {
        var value = this.get(key);
        return value == null ? Optional.empty() : Optional.ofNullable(mapper.apply(value));
    }

    /**
     * Returns the value registered by the given key or the default value
     * specified if null.
     *
     * @param key          the key
     * @param defaultValue the default value
     *
     * @return the value registered by the given key or the default value
     * specified if null.
     */
    public VALUE getOrDefault(KEY key, VALUE defaultValue) {
        return content.getOrDefault(key, defaultValue);
    }

    /**
     * Registers a new value into this registry with the given key.
     *
     * @param key   the key
     * @param value the value
     *
     * @return a new value into this registry with the given key.
     */
    public VALUE register(KEY key, VALUE value) {
        return content.put(key, value);
    }
}
