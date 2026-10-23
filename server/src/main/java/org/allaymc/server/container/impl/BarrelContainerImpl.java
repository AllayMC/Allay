package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code BarrelContainerImpl} supplies 27-slot item storage for the barrel container
 * layout. Its inherited block position identifies the barrel interface when a viewer
 * opens it. Its constructor selects the shared {@code BARREL} type, and the base
 * implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class BarrelContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty barrel storage using the shared {@code BARREL} container type.
     */
    public BarrelContainerImpl() {
        super(ContainerTypes.BARREL);
    }
}
