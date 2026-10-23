package org.allaymc.api.pdc;

/**
 * {@code PersistentDataViewHolder} represents an object that exposes custom persistent metadata
 * for inspection. The returned view has no editing operations, while objects implementing
 * {@link PersistentDataHolder} also provide a mutable container for the same namespaced custom
 * entries and their persistence path.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataViewHolder {
    /**
     * Returns the view of this object's custom metadata entries. The view exposes custom data
     * rather than the object's built-in Minecraft tags, and the interface does not guarantee a
     * snapshot of mutable backing storage.
     */
    PersistentDataContainerView getPersistentDataContainer();
}
