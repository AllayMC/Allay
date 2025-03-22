package org.allaymc.api.container.impl;

import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IWareQ
 */
@Setter
public class DoubleChestContainer extends BlockContainer {
    private static final int CHEST_SIZE = 27;

    private Container left;
    private Container right;

    public DoubleChestContainer() {
        super(FullContainerType.DOUBLE_CHEST);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        if (slot < CHEST_SIZE) {
            return left.getItemStack(slot);
        }

        return right.getItemStack(slot - CHEST_SIZE);
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack, boolean send) {
        if (slot < CHEST_SIZE) {
            left.setItemStack(slot, itemStack, send);
            return;
        }

        right.setItemStack(slot - CHEST_SIZE, itemStack, send);
    }

    @Override
    public List<ItemData> toNetworkItemData() {
        List<ItemData> networkItems = new ArrayList<>();
        networkItems.addAll(left.toNetworkItemData());
        networkItems.addAll(right.toNetworkItemData());
        return networkItems;
    }
}
