package org.allaymc.api.pdc;

import org.allaymc.api.utils.identifier.Identifier;

import java.util.Map;

/**
 * Represents a modifiable persistent data container capable of storing custom tags.
 *
 * <p>
 * This interface extends {@link PersistentDataContainerView}, adding mutation operations.
 * </p>
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataContainer extends PersistentDataContainerView {
    /**
     * @see #has(Identifier, PersistentDataType)
     */
    default boolean has(Identifier key) {
        return has(key, null);
    }

    /**
     * Checks if a value exists under the given key and matches the specified primitive data type.
     *
     * <p>
     * This method returns {@code true} only if the stored value has the same primitive
     * type as the provided {@link PersistentDataType}.
     * </p>
     *
     * <p>
     * Since custom {@link PersistentDataType} implementations store only primitive
     * representations, complex types aren't checked.
     * For example, storing a UUID as a byte array will match {@link PersistentDataType#BYTE_ARRAY},
     * and vice versa, even if the byte array doesn't contain a valid UUID.
     * </p>
     *
     * <p>
     * This method applies only to custom object keys. Overwriting standard tags,
     * such as display names, will not work as those are stored using a namespace.
     * </p>
     *
     * @param key  the key under which the value is stored
     * @param type the expected primitive data type
     * @param <P>  the primitive type stored in the container
     * @param <C>  the complex type returned by the {@link PersistentDataType}
     *
     * @return {@code true} if a value with the given key and type exists; otherwise, {@code false}
     *
     * @throws IllegalArgumentException if {@code key} or {@code type} is {@code null}
     */
    <P, C> boolean has(Identifier key, PersistentDataType<P, C> type);

    /**
     * Stores a metadata value under the given key.
     *
     * <p>
     * This API doesn't modify Minecraft’s built-in data, as all values are stored
     * under your namespace.
     * Calling this method overwrites any existing value associated with the given key.
     * </p>
     *
     * @param key   the key under which the value will be stored
     * @param type  the data type of the stored value
     * @param value the value to store
     * @param <P>   the primitive type stored in the container
     * @param <C>   the complex type returned by the {@link PersistentDataType}
     *
     * @throws IllegalArgumentException if {@code key}, {@code type}, or {@code value} is {@code null}
     * @throws IllegalArgumentException if no suitable adapter is found for {@link PersistentDataType#getPrimitiveType()}
     * @see #remove(Identifier) for removing a stored value
     */
    <P, C> void set(Identifier key, PersistentDataType<P, C> type, C value);

    /**
     * Stores all entries from the provided map in the container.
     *
     * @param map a map of keys and values to store
     */
    void putAll(Map<String, Object> map);

    /**
     * Removes a value stored under the given key.
     *
     * @param key the key to remove
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    void remove(Identifier key);

    /**
     * Clears all entries in the container.
     */
    void clear();
}
