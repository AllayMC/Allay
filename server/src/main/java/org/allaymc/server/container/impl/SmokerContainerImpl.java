package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code SmokerContainerImpl} supplies 3-slot item storage for the smoker container
 * layout. The inherited furnace accessors and sided rules use the smoker container
 * identity for network registration. Its constructor selects the shared {@code SMOKER}
 * type, and the base implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class SmokerContainerImpl extends FurnaceContainerImpl {
    /**
     * Creates empty smoker storage using the shared {@code SMOKER} container type.
     */
    public SmokerContainerImpl() {
        super(ContainerTypes.SMOKER);
    }
}
