package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerCursorContainer extends PlayerContainer {
    public PlayerCursorContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.CURSOR, playerSupplier);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.CURSOR;
    }
}
