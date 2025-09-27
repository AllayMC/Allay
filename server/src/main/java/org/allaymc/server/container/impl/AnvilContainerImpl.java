package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.AnvilContainer;

/**
 * @author IWareQ
 */
public class AnvilContainerImpl extends BlockContainerImpl implements AnvilContainer {
    public AnvilContainerImpl() {
        super(ContainerTypes.ANVIL);
    }
}
