package org.allaymc.api.registry;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * {@code DoubleKeyMappedRegistry} stores values in two independently keyed
 * indexes for alternate lookup paths. Registration writes the same value into
 * both maps, but each map retains its own existing associations. Reusing one
 * key does not remove older entries under the other key, so callers requiring a
 * one-to-one correspondence must maintain that relationship when selecting
 * keys.
 *
 * <p>
 * Mutations through registry methods share the registry monitor, while direct
 * access to the exposed maps bypasses it. Freezing replaces both maps together
 * with unmodifiable shallow snapshots after both copies succeed. Values remain
 * shared, and readers require appropriate publication or coordination when
 * freezing races with unsynchronized access. Registration does not roll back
 * the first map update if the second update fails.
 *
 * @author daoge_cmd
 */
public class DoubleKeyMappedRegistry<K1, K2, VALUE> extends AbstractRegistry<DoubleKeyMappedRegistry.MapPair<K1, K2, VALUE>> {
    /**
     * Loads the pair of key indexes immediately from the supplied input.
     * Neither map is copied during construction, so the loader's references
     * initially share all mappings with this registry.
     */
    protected <INPUT> DoubleKeyMappedRegistry(INPUT input, RegistryLoader<INPUT, MapPair<K1, K2, VALUE>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Loads a registry with {@code null} input, passes it to {@code setter},
     * then invokes {@code afterSet}. A {@code setter} failure skips
     * {@code afterSet}; an {@code afterSet} failure does not undo publication
     * by {@code setter}.
     */
    public static <I, K1, K2, VALUE> void create(RegistryLoader<I, MapPair<K1, K2, VALUE>> registryLoader, Consumer<DoubleKeyMappedRegistry<K1, K2, VALUE>> setter, Runnable afterSet) {
        var registry = new DoubleKeyMappedRegistry<>(null, registryLoader);
        setter.accept(registry);
        afterSet.run();
    }

    /**
     * Returns the value stored under the first kind of key. Missing keys
     * normally return {@code null} according to {@code m1}'s lookup rules;
     * returned values are returned without copying the indexed objects.
     */
    public VALUE getByK1(K1 k1) {
        return content.m1.get(k1);
    }

    /**
     * Returns the value stored under the second kind of key. Missing keys
     * normally return {@code null} according to {@code m2}'s lookup rules;
     * returned values are the indexed objects rather than copies.
     */
    public VALUE getByK2(K2 k2) {
        return content.m2.get(k2);
    }

    /**
     * Returns the first-index mapping or a default for an absent key. The map's
     * default-value semantics apply, so an explicitly mapped {@code null} does
     * not ordinarily select {@code defaultValue}.
     */
    public VALUE getByK1OrDefault(K1 k1, VALUE defaultValue) {
        return content.m1.getOrDefault(k1, defaultValue);
    }

    /**
     * Returns the second-index mapping or a default for an absent key. The
     * map's default-value semantics apply, so an explicitly mapped {@code null}
     * does not ordinarily select {@code defaultValue}.
     */
    public VALUE getByK2OrDefault(K2 k2, VALUE defaultValue) {
        return content.m2.getOrDefault(k2, defaultValue);
    }

    /**
     * Associates the same value with one key in each index. The registry
     * monitor covers the frozen-state check and both writes, but direct map
     * access bypasses that monitor. A frozen registry throws
     * {@link IllegalStateException} before either write.
     *
     * <p>
     * The first index changes before the second, without rollback if the second
     * write fails. Replacing one association does not remove older entries
     * under other keys, so callers must manage any stronger correspondence
     * required between the indexes.
     */
    public synchronized void register(K1 k1, K2 k2, VALUE value) {
        ensureMutable();
        // Both writes share the registry monitor, but the first association remains
        // if the second map rejects its key or value; no compensation is attempted.
        content.m1.put(k1, value);
        content.m2.put(k2, value);
    }

    /**
     * Replaces both indexes with unmodifiable shallow snapshots and freezes
     * registry mutations. Repeated calls retain the existing snapshots; values
     * remain shared, and previously exposed mutable maps become detached from
     * subsequent registry lookups. Callers must coordinate external map writers
     * while copying occurs.
     *
     * <p>
     * Each copy retains its source map's iteration order. The pair is replaced
     * only after both copies succeed, so a copy failure leaves the previous
     * pair and frozen flag unchanged.
     */
    @Override
    public synchronized void freeze() {
        if (frozen) {
            return;
        }
        // Build both wrappers privately so getContent cannot return a pair
        // containing one old index and one newly copied index during publication.
        content = new MapPair<>(
                Collections.unmodifiableMap(new LinkedHashMap<>(content.m1)),
                Collections.unmodifiableMap(new LinkedHashMap<>(content.m2))
        );
        super.freeze();
    }

    /**
     * {@code MapPair} holds the two map references used for alternate registry
     * lookups. Its canonical constructor retains {@code m1} and {@code m2}
     * directly without validating their mutual correspondence or rejecting
     * {@code null} references.
     *
     * @param m1 first key index
     * @param m2 second key index
     */
    public record MapPair<K1, K2, VALUE>(Map<K1, VALUE> m1, Map<K2, VALUE> m2) {
    }
}
