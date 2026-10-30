package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BrewingStandContainer;

/**
 * {@code BrewingStandContainerImpl} supplies 5-slot item storage for the brewing stand
 * container layout. The brewing interface supplies reagent, bottle, and fuel accessors
 * together with sided transfer rules. Its constructor selects the shared {@code
 * BREWING_STAND} type, and the base implementation manages item assignment and container
 * observers.
 *
 * @author IWareQ
 */
public class BrewingStandContainerImpl extends BlockContainerImpl implements BrewingStandContainer {
    /**
     * Creates empty brewing stand storage using the shared {@code BREWING_STAND}
     * container type.
     */
    public BrewingStandContainerImpl() {
        super(ContainerTypes.BREWING_STAND);
    }
}
