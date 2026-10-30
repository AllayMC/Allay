package org.allaymc.api.pdc;

/**
 * {@code PersistentDataHolder} represents an object with editable custom persistent metadata.
 * Its container stores namespaced entries separately from the object's built-in Minecraft data,
 * and its more specific return type lets callers update the same metadata exposed through
 * {@link PersistentDataViewHolder}.
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataHolder extends PersistentDataViewHolder {
    /**
     * Returns the object's editable custom metadata container. Changes affect custom entries used
     * by the object's persistence path; the container does not provide access to replace the
     * object's
     * built-in Minecraft tags.
     */
    PersistentDataContainer getPersistentDataContainer();
}
