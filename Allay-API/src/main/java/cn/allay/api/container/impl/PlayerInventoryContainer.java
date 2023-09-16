package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.ItemStack;
import lombok.Getter;
import lombok.Setter;
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
        super(FullContainerType.PLAYER_INVENTORY);
    }

    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }

}
