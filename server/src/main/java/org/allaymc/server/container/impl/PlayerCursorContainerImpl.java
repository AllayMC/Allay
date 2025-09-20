package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerCursorContainerImpl extends AbstractPlayerContainer {
    public PlayerCursorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerType.CURSOR, playerSupplier);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.CURSOR;
    }
}
