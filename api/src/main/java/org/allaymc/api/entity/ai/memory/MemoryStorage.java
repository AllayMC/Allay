package org.allaymc.api.entity.ai.memory;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Storage for entity AI memory data.
 *
 * @author daoge_cmd
 */
public interface MemoryStorage {

    /**
     * Store a value for the given memory type.
     *
     * @param type  the memory type
     * @param value the value to store
     * @param <T> the type of data
     */
    <T> void put(MemoryType<T> type, T value);

    /**
     * Retrieve the value for the given memory type, or its default value if not set.
     *
     * @param type the memory type
     * @param <T> the type of data
     * @return the stored value, or the default value
     */
    <T> T get(MemoryType<T> type);

    /**
     * Get an unmodifiable view of all stored memory entries.
     *
     * @return all memory entries
     */
    @UnmodifiableView
    Map<MemoryType<?>, Object> getAll();

    /**
     * Clear all stored memory data.
     */
    void clear();

    /**
     * Check if this memory storage has no data.
     *
     * @return {@code true} if empty
     */
    boolean isEmpty();

    /**
     * Check if a specific memory type has no value stored (null or absent).
     *
     * @param type the memory type
     * @param <T> the type of data
     * @return {@code true} if empty for this type
     */
    <T> boolean isEmpty(MemoryType<T> type);

    /**
     * Check if this memory storage has data.
     *
     * @return {@code true} if not empty
     */
    default boolean notEmpty() {
        return !isEmpty();
    }

    /**
     * Check if a specific memory type has a value stored.
     *
     * @param type the memory type
     * @param <T> the type of data
     * @return {@code true} if not empty for this type
     */
    default <T> boolean notEmpty(MemoryType<T> type) {
        return !isEmpty(type);
    }

    /**
     * Clear the value for a specific memory type.
     *
     * @param type the memory type to clear
     * @param <T> the type of data
     */
    <T> void clear(MemoryType<T> type);

    /**
     * Compare a stored memory value with the given value using {@code equals}.
     *
     * @param type  the memory type
     * @param value the value to compare
     * @param <T> the type of data
     * @return {@code true} if the stored value equals the given value
     */
    <T> boolean compareDataTo(MemoryType<T> type, T value);
}
