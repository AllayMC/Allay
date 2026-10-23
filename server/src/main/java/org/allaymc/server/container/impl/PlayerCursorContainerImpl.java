package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * {@code PlayerCursorContainerImpl} stores the single stack carried by a player's
 * inventory cursor. It uses the player-container notification path and fixed cursor
 * inventory ID, allowing stack-request processing to synchronize cursor contents without
 * opening a separate world container window.
 *
 * @author daoge_cmd
 */
public class PlayerCursorContainerImpl extends AbstractPlayerContainer {
    /**
     * Creates empty cursor storage using the supplied player resolver for later
     * notifications. The single stored stack participates in ordinary container
     * operations and built-in player inventory synchronization.
     */
    public PlayerCursorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.CURSOR, playerSupplier);
    }

    /**
     * Returns the fixed cursor inventory ID used for built-in content updates.
     */
    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.CURSOR;
    }
}
