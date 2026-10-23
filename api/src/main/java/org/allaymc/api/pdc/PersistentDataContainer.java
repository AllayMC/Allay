package org.allaymc.api.pdc;

import org.allaymc.api.utils.identifier.Identifier;

import java.util.Map;

/**
 * {@code PersistentDataContainer} stores mutable custom metadata under namespaced identifiers.
 * It extends the read operations in {@link PersistentDataContainerView} with replacement, removal,
 * bulk insertion, and clearing; owning items or entities persist this metadata separately from
 * their built-in Minecraft tags.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataContainer extends PersistentDataContainerView {
    /**
     * Checks presence by delegating to {@code has(key, null)}. Implementations that inherit this
     * default must handle the {@code null} type marker; implementations can instead override this
     * operation to inspect the key directly.
     */
    default boolean has(Identifier key) {
        return has(key, null);
    }

    /**
     * Returns whether the stored tag matches the supplied primitive adapter. Complex values are
     * not
     * decoded for this check, so matching a byte-array representation does not establish that a
     * custom UUID converter can decode its contents. List adapters can match the list container
     * without validating individual elements.
     *
     * @param key metadata identifier to inspect
     * @param type adapter whose primitive representation is checked
     * @throws IllegalArgumentException if a present value requires an unregistered primitive adapter
     */
    <P, C> boolean has(Identifier key, PersistentDataType<P, C> type);

    /**
     * Converts and stores {@code value}, replacing any entry under {@code key}. The supplied type
     * must produce a primitive supported by the registry; mutable primitive values can remain
     * shared
     * with the container rather than being copied.
     *
     * @throws IllegalArgumentException if the primitive type has no adapter or conversion produces an incompatible value
     */
    <P, C> void set(Identifier key, PersistentDataType<P, C> type, C value);

    /**
     * Copies raw entries into this container, replacing existing keys. Values are already encoded
     * NBT representations rather than complex converter inputs, and mutable entry values can
     * remain
     * shared with the supplied map.
     */
    void putAll(Map<String, Object> map);

    /**
     * Removes the entry under {@code key}, leaving other custom entries unchanged.
     */
    void remove(Identifier key);

    /**
     * Removes every stored custom entry from this container at once.
     */
    void clear();
}
