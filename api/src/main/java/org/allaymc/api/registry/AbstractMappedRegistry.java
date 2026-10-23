package org.allaymc.api.registry;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * {@code AbstractMappedRegistry} provides key-based registration and lookup
 * over a loader-supplied map. The concrete map determines accepted keys,
 * {@code null} values, and iteration behavior. Registry mutations check the
 * inherited frozen flag, while reads delegate directly to the current map and
 * do not acquire the registry monitor.
 *
 * @author daoge_cmd
 */
public abstract class AbstractMappedRegistry<KEY, VALUE, MAPPING extends Map<KEY, VALUE>> extends AbstractRegistry<MAPPING> {
    /**
     * Loads the backing map immediately from the supplied input. The map is
     * retained without copying, so any references held by the loader continue
     * to address the same content until replacement.
     */
    protected <INPUT> AbstractMappedRegistry(INPUT input, RegistryLoader<INPUT, MAPPING> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Returns the mapped value using the backing map's lookup rules. A missing
     * key normally returns {@code null}; returned values are shared, and a map
     * permitting {@code null} values cannot distinguish absence through this
     * call.
     */
    public VALUE get(KEY key) {
        return content.get(key);
    }

    /**
     * Maps a nonnull registered value into an optional result. A {@code null}
     * lookup skips {@code mapper}, and a {@code null} mapper result becomes an
     * empty optional; mapping does not replace the registered value.
     */
    public <U> Optional<U> map(KEY key, Function<? super VALUE, ? extends U> mapper) {
        var value = this.get(key);
        return value == null ? Optional.empty() : Optional.ofNullable(mapper.apply(value));
    }

    /**
     * Returns the mapped value or the supplied default for an absent key. The
     * backing map's {@link Map#getOrDefault} semantics apply, so an explicitly
     * mapped {@code null} does not ordinarily select {@code defaultValue}.
     */
    public VALUE getOrDefault(KEY key, VALUE defaultValue) {
        return content.getOrDefault(key, defaultValue);
    }

    /**
     * Associates a value with the key and returns the previous mapping. The
     * registry monitor covers the freeze check and map update; key and value
     * restrictions come from the backing map. A frozen registry rejects the
     * call with {@link IllegalStateException}. The result is the previous
     * value, or {@code null} when the map reports no previous value.
     */
    public synchronized VALUE register(KEY key, VALUE value) {
        ensureMutable();
        return content.put(key, value);
    }

}
