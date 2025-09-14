package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.CartographyTableContainer;

/**
 * @author IWareQ
 */
public class CartographyTableContainerImpl extends BlockContainerImpl implements CartographyTableContainer {
    public CartographyTableContainerImpl() {
        super(ContainerType.CARTOGRAPHY_TABLE);
    }
}
