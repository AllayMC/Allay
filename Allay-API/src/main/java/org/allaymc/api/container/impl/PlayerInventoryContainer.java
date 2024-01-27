package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainer extends BaseContainer {

    @Setter
    protected int handSlot = 0;
    protected EntityPlayer player;

    public PlayerInventoryContainer(EntityPlayer player) {
        super(FullContainerType.PLAYER_INVENTORY);
        this.player = player;
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
        //因为尽管客户端没有打开player inventory，但是他始终能看到自己的物品栏。所以说我们需要给客户端也发送库存包
        if (!viewers.containsValue(player)) {
            player.onSlotChange(this, slot);
        }
    }
}
