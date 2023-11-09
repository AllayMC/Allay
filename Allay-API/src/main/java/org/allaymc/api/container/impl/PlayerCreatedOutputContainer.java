package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

import static org.allaymc.api.container.FullContainerType.CREATED_OUTPUT;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
public class PlayerCreatedOutputContainer extends BaseContainer {

    public PlayerCreatedOutputContainer() {
        super(CREATED_OUTPUT);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        //HACK: 别问我为什么mj会把CREATED_OUTPUT的输出槽位定成50，我也不知道
        return super.getItemStack(0);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        return super.getSlotType(0);
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack) {
        super.setItemStack(0, itemStack);
    }
}
