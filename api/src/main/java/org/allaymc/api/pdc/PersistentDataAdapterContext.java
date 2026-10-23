package org.allaymc.api.pdc;

/**
 * {@code PersistentDataAdapterContext} supplies new containers to persistent data converters that
 * need nested values. Containers created by a context use the associated type registry, so custom
 * converters can build compound values with the same supported primitive representations as their
 * owning container.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataAdapterContext {
    /**
     * Creates an empty container using this context's associated type registry.
     */
    PersistentDataContainer newPersistentDataContainer();
}
