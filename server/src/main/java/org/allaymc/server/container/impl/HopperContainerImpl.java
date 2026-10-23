package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code HopperContainerImpl} supplies 5-slot item storage for the hopper container
 * layout. The block entity performs transfer selection while this container exposes its
 * stored stacks and slot notifications. Its constructor selects the shared {@code
 * HOPPER} type, and the base implementation manages item assignment and container
 * observers.
 *
 * @author ClexaGod
 */
public class HopperContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty hopper storage using the shared {@code HOPPER} container type.
     */
    public HopperContainerImpl() {
        super(ContainerTypes.HOPPER);
    }
}
