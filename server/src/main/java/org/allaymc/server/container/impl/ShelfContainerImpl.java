package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code ShelfContainerImpl} supplies 3-slot item storage for the shelf container
 * layout. Shelf block logic uses this storage while the base container supplies item
 * access and slot-change notification. Its constructor selects the shared {@code SHELF}
 * type, and the base implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class ShelfContainerImpl extends BaseContainer {
    /**
     * Creates empty shelf storage using the shared {@code SHELF} container type.
     */
    public ShelfContainerImpl() {
        super(ContainerTypes.SHELF);
    }
}
