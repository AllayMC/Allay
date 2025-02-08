package org.allaymc.api.pdc;

/**
 * The {@link PersistentDataHolder} interface defines an object that can store
 * custom persistent metadata on it.
 * <p>Prefer using {@link PersistentDataViewHolder} for read-only operations
 * as it covers more types.</p>
 */
public interface PersistentDataHolder extends PersistentDataViewHolder {
    /**
     * Returns a custom tag container capable of storing tags on the object.
     * <p>
     * Note that the tags stored on this container are all stored under their
     * own custom namespace therefore modifying default tags using this
     * {@link PersistentDataHolder} is impossible.
     *
     * @return the persistent metadata container
     */
    PersistentDataContainer getPersistentDataContainer();
}
