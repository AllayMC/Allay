package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BeaconContainer;

/**
 * @author daoge_cmd
 */
public class BeaconContainerImpl extends BlockContainerImpl implements BeaconContainer {
    public BeaconContainerImpl() {
        super(ContainerTypes.BEACON);
    }
}
