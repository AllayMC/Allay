package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.AnvilContainer;

/**
 * {@code AnvilContainerImpl} supplies 3-slot item storage for the anvil container
 * layout. Its named input, material, and result accessors are supplied by {@code
 * AnvilContainer}. Its constructor selects the shared {@code ANVIL} type, and the base
 * implementation manages item assignment and container observers.
 *
 * @author IWareQ
 */
public class AnvilContainerImpl extends BlockContainerImpl implements AnvilContainer {
    /**
     * Creates empty anvil storage using the shared {@code ANVIL} container type.
     */
    public AnvilContainerImpl() {
        super(ContainerTypes.ANVIL);
    }
}
