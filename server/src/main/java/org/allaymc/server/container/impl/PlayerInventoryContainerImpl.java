package org.allaymc.server.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.PlayerInventoryContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainerImpl extends AbstractPlayerContainer implements PlayerInventoryContainer {

    @Setter
    protected int handSlot = 0;

    public PlayerInventoryContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerType.PLAYER_INVENTORY, playerSupplier);
    }

    @Override
    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    @Override
    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }

    @Override
    public void clearItemInHand() {
        clearSlot(handSlot);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.PLAYER_INVENTORY;
    }
}
