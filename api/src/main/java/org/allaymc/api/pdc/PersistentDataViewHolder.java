package org.allaymc.api.pdc;

/**
 * The {@link PersistentDataViewHolder} interface defines an object that can access custom persistent data.
 *
 * <p>
 * This interface is designed for viewing persistent data without modifying it.
 * If modifications are needed, {@link PersistentDataHolder} should be used instead.
 * </p>
 *
 * @author IWareQ | Bukkit
 */
public interface PersistentDataViewHolder {
    /**
     * Returns a custom tag container view for accessing tags stored on the object.
     *
     * <p>
     * Tags in this container are stored under a custom namespace,
     * so modifying default tags (for example, Minecraft's built-in tags) via this view is not possible.
     * </p>
     *
     * @return the persistent data container view for accessing stored tags
     */
    PersistentDataContainerView getPersistentDataContainer();
}
