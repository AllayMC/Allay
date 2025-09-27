package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.CartographyTableContainer;

/**
 * @author IWareQ
 */
public class CartographyTableContainerImpl extends BlockContainerImpl implements CartographyTableContainer {
    public CartographyTableContainerImpl() {
        super(ContainerTypes.CARTOGRAPHY_TABLE);
    }
}
