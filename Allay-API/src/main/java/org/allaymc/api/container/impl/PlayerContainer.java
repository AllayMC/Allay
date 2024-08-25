package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * Allay Project 2024/8/25
 *
 * @author daoge_cmd
 */
public abstract class PlayerContainer extends BaseContainer {

    protected Supplier<EntityPlayer> playerSupplier;

    public PlayerContainer(FullContainerType<? extends Container> containerType, Supplier<EntityPlayer> playerSupplier) {
        super(containerType);
        this.playerSupplier = playerSupplier;
    }

    @Override
    public void onSlotChange(int slot) {
        super.onSlotChange(slot);
        // Because even if the client has not opened player container, they can always see their own hot bar.
        // Therefore, we need to send an inventory packet to the client as well.
        var player = playerSupplier.get();
        if (!viewers.containsValue(player)) {
            player.onSlotChange(this, slot);
        }
    }

    public abstract int getUnopenedContainerId();
}
