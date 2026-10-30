package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.GrindstoneContainer;

/**
 * {@code GrindstoneContainerImpl} supplies 3-slot item storage for the grindstone
 * container layout. The grindstone interface supplies accessors for its two inputs and
 * proposed result slot. Its constructor selects the shared {@code GRINDSTONE} type, and
 * the base implementation manages item assignment and container observers.
 *
 * @author IWareQ
 */
public class GrindstoneContainerImpl extends BlockContainerImpl implements GrindstoneContainer {
    /**
     * Creates empty grindstone storage using the shared {@code GRINDSTONE} container
     * type.
     */
    public GrindstoneContainerImpl() {
        super(ContainerTypes.GRINDSTONE);
    }
}
