package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.LoomContainer;

/**
 * {@code LoomContainerImpl} supplies 4-slot item storage for the loom container layout.
 * The loom interface supplies accessors for banner input, dye, pattern material, and
 * result. Its constructor selects the shared {@code LOOM} type, and the base
 * implementation manages item assignment and container observers.
 *
 * @author IWareQ
 */
public class LoomContainerImpl extends BlockContainerImpl implements LoomContainer {

    /**
     * Creates empty loom storage using the shared {@code LOOM} container type.
     */
    public LoomContainerImpl() {
        super(ContainerTypes.LOOM);
    }

}
