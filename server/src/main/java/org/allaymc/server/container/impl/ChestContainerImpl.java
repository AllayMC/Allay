package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code ChestContainerImpl} supplies 27-slot item storage for the single chest
 * container layout. A double chest can delegate one half of its virtual storage to this
 * container. Its constructor selects the shared {@code CHEST} type, and the base
 * implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class ChestContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty single chest storage using the shared {@code CHEST} container type.
     */
    public ChestContainerImpl() {
        super(ContainerTypes.CHEST);
    }
}
