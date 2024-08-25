package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainer extends PlayerContainer {

    @Setter
    protected int handSlot = 0;

    public PlayerInventoryContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.PLAYER_INVENTORY, playerSupplier);
    }

    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }

    public void clearItemInHand() {
        clearSlot(handSlot);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.PLAYER_INVENTORY;
    }
}
