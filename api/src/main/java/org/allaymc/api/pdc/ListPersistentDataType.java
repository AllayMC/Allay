package org.allaymc.api.pdc;

import java.util.List;

/**
 * {@code ListPersistentDataType} represents a persistent list whose elements use another
 * persistent
 * data type for conversion. The element adapter supplies the NBT element representation, while
 * the list adapter exposes {@code List.class} as both its primitive and complex container class.
 *
 * @param <P> primitive element representation
 * @param <C> converted element representation
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
