package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BeaconContainer;

/**
 * {@code BeaconContainerImpl} supplies 1-slot item storage for the beacon container
 * layout. Its payment-slot accessor lets beacon action processing consume the stored
 * offering. Its constructor selects the shared {@code BEACON} type, and the base
 * implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class BeaconContainerImpl extends BlockContainerImpl implements BeaconContainer {
    /**
     * Creates empty beacon storage using the shared {@code BEACON} container type.
     */
    public BeaconContainerImpl() {
        super(ContainerTypes.BEACON);
    }
}
