package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.LoomContainer;

/**
 * @author IWareQ
 */
public class LoomContainerImpl extends BlockContainerImpl implements LoomContainer {

    public LoomContainerImpl() {
        super(ContainerTypes.LOOM);
    }

}
