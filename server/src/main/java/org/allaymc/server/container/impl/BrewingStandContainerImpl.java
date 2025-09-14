package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.BrewingStandContainer;

/**
 * @author IWareQ
 */
public class BrewingStandContainerImpl extends BlockContainerImpl implements BrewingStandContainer {
    public BrewingStandContainerImpl() {
        super(ContainerType.BREWING_STAND);
    }
}
