package org.allaymc.api.pdc;

/**
 * The {@link PersistentDataHolder} interface defines an object that can store custom persistent metadata.
 *
 * <p>
 * For read-only operations, prefer using {@link PersistentDataViewHolder}, as it supports a wider range of types.
 * </p>
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataHolder extends PersistentDataViewHolder {
    /**
     * Returns a custom tag container for storing tags on the object.
     * <p>
     * Note that all tags stored in this container are placed under a custom namespace.
     * This means that modifying default tags (such as Minecraft's built-in tags) via this container is not possible.
     * </p>
     *
     * @return the persistent metadata container associated with this object
     */
    PersistentDataContainer getPersistentDataContainer();
}
