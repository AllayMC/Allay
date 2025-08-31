package org.allaymc.api.pdc;

import java.util.List;

/**
 * Represents a persistent data type capable of storing a list of elements within a {@link PersistentDataContainer}.
 *
 * @param <P> the primitive type of the list elements
 * @param <C> the complex type of the list elements
 *
 * @author IWareQ | Bukkit
 */
public interface ListPersistentDataType<P, C> extends PersistentDataType<List<P>, List<C>> {
    /**
     * Returns the persistent data type of the elements contained in the list.
     *
     * @return the persistent data type of the list elements
     */
    PersistentDataType<P, C> getElementType();
}
