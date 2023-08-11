package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.ItemStack;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainer extends BaseContainer {

    @Setter
    @Range(from = 0, to = 8)
    protected int handSlot = 0;

    public PlayerInventoryContainer() {
        super(36);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        if (slot >= 0 && slot <= 8) {
            return ContainerSlotType.HOTBAR;
        } else if (slot >= 9 && slot <= 35) {
            return ContainerSlotType.INVENTORY;
        } else {
            throw new IndexOutOfBoundsException("Slot must be between 0 and 35");
        }
    }

    @Override
    public FullContainerType<PlayerInventoryContainer> getContainerType() {
        return FullContainerType.PLAYER_INVENTORY;
    }

    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }
}
