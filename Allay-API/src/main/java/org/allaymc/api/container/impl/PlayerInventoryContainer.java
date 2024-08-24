package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainer extends BaseContainer {

    @Setter
    protected int handSlot = 0;
    protected Supplier<EntityPlayer> playerSupplier;

    public PlayerInventoryContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.PLAYER_INVENTORY);
        this.playerSupplier = playerSupplier;
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
    public void onSlotChange(int slot) {
        super.onSlotChange(slot);
        // Because even if the client has not opened the player inventory, they can always see their own hot bar.
        // Therefore, we need to send an inventory packet to the client as well.
        var player = playerSupplier.get();
        if (!viewers.containsValue(player)) {
            player.onSlotChange(this, slot);
        }
    }
}
