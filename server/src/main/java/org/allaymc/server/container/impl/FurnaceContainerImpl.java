package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.FurnaceContainer;

/**
 * @author daoge_cmd
 */
public class FurnaceContainerImpl extends BlockContainerImpl implements FurnaceContainer {
    public FurnaceContainerImpl() {
        super(ContainerType.FURNACE);
    }

    public FurnaceContainerImpl(ContainerType<?> containerType) {
        super(containerType);
    }
}
