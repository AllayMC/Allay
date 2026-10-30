package org.allaymc.api.entity.ai.memory;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code MemoryStorage} stores typed AI values shared by sensors, behaviors,
 * and controllers. An absent entry can obtain its memory type's default, while
 * an explicitly stored {@code null} remains empty for typed reads until the
 * entry is cleared or replaced.
 *
 * @author daoge_cmd
 */
public interface MemoryStorage {

    /**
     * Stores the supplied value under the memory key, replacing any existing
     * value. An explicit {@code null} suppresses the key's default until the
     * entry is removed or replaced.
     */
    <T> void put(MemoryType<T> type, T value);

    /**
     * Returns the stored value or obtains the type's default for an absent
     * entry. An explicitly stored {@code null} returns {@code null}; a
     * non-{@code null} default may become a stored value.
     */
    <T> T get(MemoryType<T> type);

    /**
     * Returns an unmodifiable view of the implementation's stored entries.
     * Values remain shared, and implementations may expose their internal
     * explicit-null marker through this untyped view rather than through typed
     * {@code get} calls.
     */
    @UnmodifiableView
    Map<MemoryType<?>, Object> getAll();

    /**
     * Removes all entries from this memory storage. Subsequent typed reads can
     * obtain defaults again, including for entries whose explicit {@code null}
     * previously suppressed their type's default.
     */
    void clear();

    /**
     * Returns whether this storage contains no entries at all. An explicit
     * {@code null} still occupies an entry, so per-key emptiness can differ
     * from emptiness of the whole storage.
     */
    boolean isEmpty();

    /**
     * Returns whether the specified key is absent or explicitly contains {@code
     * null}. This check does not obtain a default, so an absent key with a
     * non-{@code null} default remains empty until read.
     */
    <T> boolean isEmpty(MemoryType<T> type);

    /**
     * Returns whether this storage contains at least one entry. An explicitly
     * stored {@code null} counts as an entry even though a typed read of that
     * key returns {@code null}.
     */
    default boolean notEmpty() {
        return !isEmpty();
    }

    /**
     * Returns whether the specified key currently holds a non-{@code null}
     * stored value. This check does not obtain the type's default or create an
     * entry for an absent key.
     */
    default <T> boolean notEmpty(MemoryType<T> type) {
        return !isEmpty(type);
    }

    /**
     * Removes the entry for the supplied memory key. A later typed read may
     * obtain the key's default again, even when the removed entry explicitly
     * stored {@code null}.
     */
    <T> void clear(MemoryType<T> type);

    /**
     * Stores a value atomically when this key is absent or explicitly empty.
     * The result is {@code true} when the replacement occurs, including when
     * the supplied replacement is {@code null}.
     */
    <T> boolean putIfAbsent(MemoryType<T> type, T value);

    /**
     * Returns whether the typed memory value equals the supplied value using
     * null-safe equality. Reading an absent key can obtain and retain its
     * default before the comparison is made.
     */
    <T> boolean compareDataTo(MemoryType<T> type, T value);
}
