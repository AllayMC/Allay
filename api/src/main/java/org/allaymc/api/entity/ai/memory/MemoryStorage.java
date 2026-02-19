package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.entity.Entity;
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
     * @param <Data> the type of data
     */
    <Data> void put(MemoryType<Data> type, Data value);

    /**
     * Retrieve the value for the given memory type, or its default value if not set.
     *
     * @param type the memory type
     * @param <Data> the type of data
     * @return the stored value, or the default value
     */
    <Data> Data get(MemoryType<Data> type);

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
     * Get the entity that owns this memory storage.
     *
     * @return the entity
     */
    Entity getEntity();

    /**
     * Set the entity that owns this memory storage.
     *
     * @param entity the entity
     */
    void setEntity(Entity entity);

    /**
     * Check if this memory storage has no data.
     *
     * @return {@code true} if empty
     */
    boolean isEmpty();

    /**
     * Check if this memory storage has data.
     *
     * @return {@code true} if not empty
     */
    default boolean notEmpty() {
        return !isEmpty();
    }

    /**
     * Compare a stored memory value with the given value using {@code equals}.
     *
     * @param type  the memory type
     * @param value the value to compare
     * @param <Data> the type of data
     * @return {@code true} if the stored value equals the given value
     */
    <Data> boolean compareDataTo(MemoryType<Data> type, Data value);
}
