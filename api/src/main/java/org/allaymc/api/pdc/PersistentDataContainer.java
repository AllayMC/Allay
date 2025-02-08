package org.allaymc.api.pdc;

import org.allaymc.api.utils.Identifier;

import java.io.IOException;

/**
 * This interface represents a map like object, capable of storing custom tags in it.
 */
public interface PersistentDataContainer extends PersistentDataContainerView {
    /**
     * @see #has(Identifier, PersistentDataType)
     */
    default boolean has(Identifier key) {
        return has(key, null);
    }

    /**
     * Returns if the persistent metadata provider has metadata registered matching the provided parameters.
     * <p>
     * This method will only return true if the found value has the same primitive data type as the provided key.
     * <p>
     * Storing a value using a custom {@link PersistentDataType} implementation will not store the complex data type.
     * Therefore, storing a UUID (by storing a byte[]) will match has(key, {@link PersistentDataType#BYTE_ARRAY}).
     * Likewise, a stored byte[] will always match your UUID {@link PersistentDataType} even if it is not 16 bytes long.
     * <p>
     * This method is only usable for custom object keys.
     * Overwriting existing tags, like the display name, will not work as the values are stored using your namespace.
     *
     * @param key  the key the value is stored under
     * @param type the type the primitive stored value has to match
     * @param <P>  the generic type of the stored primitive
     * @param <C>  the generic type of the eventually created complex object
     *
     * @return if a value with the provided key and type exists
     *
     * @throws IllegalArgumentException if the key to look up is null
     * @throws IllegalArgumentException if the type to cast the found object to is null
     */
    <P, C> boolean has(Identifier key, PersistentDataType<P, C> type);

    /**
     * Stores a metadata value on the {@link PersistentDataHolder} instance.
     * <p>
     * This API cannot be used to manipulate minecraft data, as the values will be stored using your namespace.
     * This method will override any existing value the {@link PersistentDataHolder} may have stored under the provided key.
     *
     * @param key   the key this value will be stored under
     * @param type  the type this tag uses
     * @param value the value to store in the tag
     * @param <P>   the generic java type of the tag value
     * @param <C>   the generic type of the object to store
     *
     * @throws IllegalArgumentException if the key is null
     * @throws IllegalArgumentException if the type is null
     * @throws IllegalArgumentException if the value is null. Removing a tag should be done using {@link #remove(Identifier)}
     * @throws IllegalArgumentException if no suitable adapter was found for the {@link PersistentDataType#getPrimitiveType()}
     */
    <P, C> void set(Identifier key, PersistentDataType<P, C> type, C value);

    /**
     * Removes a custom key from the {@link PersistentDataHolder} instance.
     *
     * @param key the key to remove
     *
     * @throws IllegalArgumentException if the provided key is null
     */
    void remove(Identifier key);

    /**
     * Read values from a serialized byte array into this {@link PersistentDataContainer} instance.
     *
     * @param bytes the byte array to read from
     * @param clear if true, this {@link PersistentDataContainer} instance will be cleared before reading
     *
     * @throws IOException if the byte array has an invalid format
     */
    void readFromBytes(byte[] bytes, boolean clear) throws IOException;

    /**
     * @see #readFromBytes(byte[], boolean)
     */
    default void readFromBytes(byte[] bytes) throws IOException {
        this.readFromBytes(bytes, true);
    }
}
