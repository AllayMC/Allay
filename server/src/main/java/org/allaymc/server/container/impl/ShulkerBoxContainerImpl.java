package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code ShulkerBoxContainerImpl} supplies 27-slot item storage for the shulker box
 * container layout. Its inherited block position identifies the world interface when
 * viewers open the stored contents. Its constructor selects the shared {@code
 * SHULKER_BOX} type, and the base implementation manages item assignment and container
 * observers.
 *
 * @author IWareQ
 */
public class ShulkerBoxContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty shulker box storage using the shared {@code SHULKER_BOX} container
     * type.
     */
    public ShulkerBoxContainerImpl() {
        super(ContainerTypes.SHULKER_BOX);
    }
}
