package org.allaymc.api.pdc;

import org.allaymc.api.utils.Identifier;

import java.util.Set;

/**
 * Represents a read-only view of a {@link PersistentDataContainer}.
 * No methods in this interface modify the container.
 *
 * @author IWareQ | Bukkit
 * @see PersistentDataContainer
 */
public interface PersistentDataContainerView {
    /**
     * Checks if the container has a value stored under the given key
     * and if it matches the specified primitive data type.
     *
     * <p>
     * This method returns {@code true} only if the stored value has the same
     * primitive type as the provided {@link PersistentDataType}.
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
     * This method applies only to custom object keys.
     * Overwriting standard tags, such as display names, will not work as those are stored using a namespace.
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
     * Checks if the container has a value stored under the given key,
     * regardless of its type.
     *
     * <p>
     * This method applies only to custom object keys.
     * Overwriting standard tags, such as display names, will not work as those are stored using a namespace.
     * </p>
     *
     * @param key the key under which the value is stored
     *
     * @return {@code true} if a value with the given key exists; otherwise, {@code false}
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    boolean has(Identifier key);

    /**
     * Retrieves the value stored under the given key.
     *
     * @param key  the key under which the value is stored
     * @param type the expected data type
     * @param <P>  the primitive type stored in the container
     * @param <C>  the complex type returned by the {@link PersistentDataType}
     *
     * @return the stored value or {@code null} if no matching value exists
     *
     * @throws IllegalArgumentException if {@code key} or {@code type} is {@code null}
     * @throws IllegalArgumentException if a value exists but can't be accessed with the given type
     * @throws IllegalArgumentException if no suitable adapter is found for {@link PersistentDataType#getPrimitiveType()}
     */
    <P, C> C get(Identifier key, PersistentDataType<P, C> type);

    /**
     * Retrieves the value stored under the given key, or returns a default value
     * if no such value exists.
     *
     * @param key          the key under which the value is stored
     * @param type         the expected data type
     * @param defaultValue the value to return if no stored value exists
     * @param <P>          the primitive type stored in the container
     * @param <C>          the complex type returned by the {@link PersistentDataType}
     *
     * @return the stored value or {@code defaultValue} if no matching value exists
     *
     * @throws IllegalArgumentException if {@code key} or {@code type} is {@code null}
     * @throws IllegalArgumentException if a value exists but can't be accessed with the given type
     * @throws IllegalArgumentException if no suitable adapter is found for {@link PersistentDataType#getPrimitiveType()}
     */
    <P, C> C getOrDefault(Identifier key, PersistentDataType<P, C> type, C defaultValue);

    /**
     * Returns the set of all keys present in this container.
     * <p>
     * Modifications to the returned set don't affect the underlying container.
     * </p>
     *
     * @return a set of keys stored in this container
     */
    Set<Identifier> getKeys();

    /**
     * Checks whether the container is empty.
     *
     * @return {@code true} if the container has no stored entries; otherwise, {@code false}
     */
    boolean isEmpty();

    /**
     * Returns the adapter context associated with this container.
     *
     * @return the {@link PersistentDataAdapterContext} used by this container
     */
    PersistentDataAdapterContext getAdapterContext();
}
