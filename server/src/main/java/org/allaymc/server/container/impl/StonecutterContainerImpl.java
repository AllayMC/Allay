package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.StonecutterContainer;

/**
 * @author IWareQ
 */
public class StonecutterContainerImpl extends BlockContainerImpl implements StonecutterContainer {
    public StonecutterContainerImpl() {
        super(ContainerType.STONECUTTER);
    }
}
