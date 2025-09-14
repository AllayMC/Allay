package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.GrindstoneContainer;

/**
 * @author IWareQ
 */
public class GrindstoneContainerImpl extends BlockContainerImpl implements GrindstoneContainer {
    public GrindstoneContainerImpl() {
        super(ContainerType.GRINDSTONE);
    }
}
