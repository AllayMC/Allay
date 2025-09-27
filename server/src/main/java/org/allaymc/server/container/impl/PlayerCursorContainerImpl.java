package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerCursorContainerImpl extends AbstractPlayerContainer {
    public PlayerCursorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.CURSOR, playerSupplier);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.CURSOR;
    }
}
