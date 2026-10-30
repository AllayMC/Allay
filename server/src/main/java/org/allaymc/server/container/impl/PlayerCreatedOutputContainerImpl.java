package org.allaymc.server.container.impl;

import static org.allaymc.api.container.ContainerTypes.CREATED_OUTPUT;

/**
 * {@code PlayerCreatedOutputContainerImpl} supplies 1-slot item storage for the player
 * created-output container layout. Crafting processors place synthesized results here
 * before subsequent stack-request actions consume or transfer them. Its constructor
 * selects the shared {@code CREATED_OUTPUT} type, and the base implementation manages
 * item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class PlayerCreatedOutputContainerImpl extends BaseContainer {
    /**
     * Creates empty player created-output storage using the shared {@code
     * CREATED_OUTPUT} container type.
     */
    public PlayerCreatedOutputContainerImpl() {
        super(CREATED_OUTPUT);
    }
}
