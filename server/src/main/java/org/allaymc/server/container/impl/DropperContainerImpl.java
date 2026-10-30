package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code DropperContainerImpl} supplies 9-slot item storage for the dropper container
 * layout. The block entity performs item ejection while this container supplies storage
 * and viewer notifications. Its constructor selects the shared {@code DROPPER} type, and
 * the base implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class DropperContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty dropper storage using the shared {@code DROPPER} container type.
     */
    public DropperContainerImpl() {
        super(ContainerTypes.DROPPER);
    }
}
