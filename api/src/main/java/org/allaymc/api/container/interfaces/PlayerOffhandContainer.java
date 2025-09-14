package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface PlayerOffhandContainer extends Container {
    int OFFHAND_SLOT = 0;

    default ItemStack getOffhand() {
        return getItemStack(OFFHAND_SLOT);
    }

    default void setOffhand(ItemStack itemStack) {
        setItemStack(OFFHAND_SLOT, itemStack);
    }
}
