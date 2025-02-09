package org.allaymc.api.pdc;

/**
 * Represents the context in which a {@link PersistentDataType} can serialize and deserialize values.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataAdapterContext {
    /**
     * Creates a new, empty {@link PersistentDataContainer} instance.
     *
     * @return a fresh {@link PersistentDataContainer} instance
     */
    PersistentDataContainer newPersistentDataContainer();
}
