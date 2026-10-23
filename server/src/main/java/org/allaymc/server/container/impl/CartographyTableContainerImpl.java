package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.CartographyTableContainer;

/**
 * {@code CartographyTableContainerImpl} supplies 3-slot item storage for the cartography
 * table container layout. The cartography interface supplies named input,
 * additional-material, and result accessors. Its constructor selects the shared {@code
 * CARTOGRAPHY_TABLE} type, and the base implementation manages item assignment and
 * container observers.
 *
 * @author IWareQ
 */
public class CartographyTableContainerImpl extends BlockContainerImpl implements CartographyTableContainer {
    /**
     * Creates empty cartography table storage using the shared {@code CARTOGRAPHY_TABLE}
     * container type.
     */
    public CartographyTableContainerImpl() {
        super(ContainerTypes.CARTOGRAPHY_TABLE);
    }
}
