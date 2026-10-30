package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code DispenserContainerImpl} supplies 9-slot item storage for the dispenser
 * container layout. Dispensing behavior belongs to the block entity; this container
 * supplies the shared stored stacks and notifications. Its constructor selects the
 * shared {@code DISPENSER} type, and the base implementation manages item assignment and
 * container observers.
 *
 * @author daoge_cmd
 */
public class DispenserContainerImpl extends BlockContainerImpl {
    /**
     * Creates empty dispenser storage using the shared {@code DISPENSER} container type.
     */
    public DispenserContainerImpl() {
        super(ContainerTypes.DISPENSER);
    }
}
