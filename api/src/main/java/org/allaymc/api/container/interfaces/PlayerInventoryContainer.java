package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface PlayerInventoryContainer extends Container {
    ItemStack getItemInHand();

    void setItemInHand(ItemStack itemStack);

    void clearItemInHand();

    int getHandSlot();

    void setHandSlot(int handSlot);
}
