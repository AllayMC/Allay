package org.allaymc.server.entity.ai.memory;

import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code MemoryStorageImpl} stores typed AI memories in a concurrent map and
 * distinguishes missing entries from explicit {@code null} values. Missing
 * entries obtain the key's default supplier; explicit {@code null} values use
 * {@code EMPTY_VALUE} so typed reads can suppress a non-{@code null} default.
 * Whole-storage emptiness counts these markers, while per-key emptiness treats
 * them as empty.
 *
 * <p>
 * Individual map operations are concurrent, but default lookup and insertion
 * span separate operations. Competing reads can compute multiple defaults, and
 * a late default insertion can replace another writer's value. {@code
 * putIfAbsent} resolves its per-key decision inside {@code compute}; {@code
 * getAll} exposes a live unmodifiable map view containing raw markers and
 * shared value objects.
 *
 * @author daoge_cmd
 */
public class MemoryStorageImpl implements MemoryStorage {

    /**
     * {@code EMPTY_VALUE} represents an explicit {@code null} entry in the
     * concurrent map. Typed reads decode it, while {@code getAll} exposes the
     * marker unchanged.
     */
    private static final Object EMPTY_VALUE = new Object();

    /**
     * {@code storage} retains values by memory key without copying them. Its
     * entries include explicit-null markers, and unmodifiable views still share
     * the underlying mutable values.
     */
    private final ConcurrentHashMap<MemoryType<?>, Object> storage = new ConcurrentHashMap<>();

    /**
     * Replaces the key's entry with the supplied value or an explicit-null
     * marker. Storing {@code null} preserves the entry and suppresses default
     * lookup until the key is cleared.
     */
    @Override
    public <T> void put(MemoryType<T> type, T value) {
        if (value == null) {
            storage.put(type, EMPTY_VALUE);
        } else {
            storage.put(type, value);
        }
    }

    /**
     * Returns a stored value, decodes an explicit-null marker, or obtains an
     * absent key's default. Non-{@code null} defaults are written back to the
     * map; {@code null} defaults leave the key absent and can be recomputed on
     * every read.
     *
     * <p>
     * Lookup, supplier invocation, and insertion are separate operations. A
     * concurrent writer can publish a value after the initial lookup and have
     * it overwritten by the default insertion. Callers requiring a stable
     * read-and-initialize decision must therefore coordinate that sequence
     * externally rather than relying on the map's individual operation safety.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(MemoryType<T> type) {
        var value = storage.get(type);
        if (value == null) {
            // A null default leaves this key absent and permits later recomputation. A
            // non-null default is cached separately from the initial lookup, allowing competing writes.
            var defaultData = type.defaultData().get();
            if (defaultData != null) {
                storage.put(type, defaultData);
            }
            return defaultData;
        }
        if (value == EMPTY_VALUE) {
            return null;
        }
        return (T) value;
    }

    /**
     * Returns a live unmodifiable view of the backing map. Explicit-null
     * entries expose {@code EMPTY_VALUE}, and mutable stored objects remain
     * shared with typed readers and their original owners.
     */
    @Override
    @UnmodifiableView
    public Map<MemoryType<?>, Object> getAll() {
        return Collections.unmodifiableMap(storage);
    }

    /**
     * Removes all entries, including explicit-null markers, from the backing
     * map. Later typed reads can invoke default suppliers again; previously
     * returned views observe subsequent changes to the same map.
     */
    @Override
    public void clear() {
        storage.clear();
    }

    /**
     * Removes this key's entry, including any explicit-null marker. A later
     * typed read can obtain its default again, and a concurrent writer may
     * store another entry after removal.
     */
    @Override
    public <T> void clear(MemoryType<T> type) {
        storage.remove(type);
    }

    /**
     * Returns whether the backing map contains no stored entries. Explicit-null
     * markers count as entries here even though {@code isEmpty(MemoryType)}
     * treats their individual keys as empty for typed access.
     */
    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    /**
     * Returns whether this key is absent or holds the explicit-null marker. The
     * check does not invoke its default supplier and therefore does not
     * initialize an absent memory.
     */
    @Override
    public <T> boolean isEmpty(MemoryType<T> type) {
        var v = storage.get(type);
        return v == null || v == EMPTY_VALUE;
    }

    /**
     * Stores the supplied value when the key is absent or explicitly empty. The
     * {@code compute} operation makes the decision and replacement together for
     * this key, preventing competing insertions through this method from both
     * replacing the same nonempty entry.
     *
     * <p>
     * An explicit-null marker remains replaceable, unlike a non-{@code null}
     * stored value. Supplying {@code null} inserts that marker and still
     * returns {@code true}; another invocation may then replace it again. The
     * result describes this invocation's decision rather than guaranteeing that
     * the value remains present after concurrent operations.
     */
    @Override
    public <T> boolean putIfAbsent(MemoryType<T> type, T value) {
        boolean[] inserted = {false};
        // Treat the explicit-null marker as replaceable inside the same per-key
        // computation, so testing emptiness cannot race separately from publishing this replacement.
        storage.compute(type, (k, existing) -> {
            if (existing == null || existing == EMPTY_VALUE) {
                inserted[0] = true;
                return value != null ? value : EMPTY_VALUE;
            }
            return existing;
        });
        return inserted[0];
    }

    /**
     * Compares the typed value with the supplied value using null-safe
     * equality. The typed read can initialize an absent default, so this
     * comparison may also add a stored entry.
     */
    @Override
    public <T> boolean compareDataTo(MemoryType<T> type, T value) {
        return Objects.equals(get(type), value);
    }
}
