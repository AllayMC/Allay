package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.ContainerViewer;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.ItemStack;
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
    public void onSlotChange(int slot, ItemStack current) {
        super.onSlotChange(slot - 1, current);
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
