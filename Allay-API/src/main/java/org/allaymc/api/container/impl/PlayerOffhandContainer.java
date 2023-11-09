package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerOffhandContainer extends BaseContainer {
    public PlayerOffhandContainer() {
        super(FullContainerType.OFFHAND);
    }

    //do not ask me why client send 1 instead of 0 for offhand slot, go and ask mojang :c

    @Override
    public void setItemStack(int slot, ItemStack itemStack) {
        super.setItemStack(slot - 1, itemStack);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        return super.getItemStack(slot - 1);
    }

    @Override
    public void onSlotChange(int slot) {
        super.onSlotChange(slot - 1);
    }

    @Override
    public ContainerSlotType getSlotType(int slot) {
        return super.getSlotType(slot - 1);
    }

    @Override
    public void sendContent(ContainerViewer viewer, int slot) {
        super.sendContent(viewer, slot - 1);
    }
}
