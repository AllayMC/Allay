package org.allaymc.server.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.InventoryContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
@Getter
public class InventoryContainerImpl extends AbstractPlayerContainer implements InventoryContainer {

    @Setter
    protected int handSlot = 0;

    private boolean syncingHand = false;

    public InventoryContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.INVENTORY, playerSupplier);
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
    public void notifySlotChange(int slot, boolean send) {
        super.notifySlotChange(slot, send);
        // When the hand slot changes, sync the hand item to all viewers
        if (!syncingHand && slot == handSlot) {
            syncingHand = true;
            try {
                var player = getPlayer();
                if (player != null) {
                    var entity = player.getControlledEntity();
                    if (entity != null) {
                        entity.notifyItemInHandChange();
                    }
                }
            } finally {
                syncingHand = false;
            }
        }
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.PLAYER_INVENTORY;
    }
}
