package org.allaymc.api.item.init;

import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemStackInitInfo<T extends ItemStack> extends ComponentInitInfo {

    int count();

    int meta();

    NbtMap extraTag();

    int stackNetworkId();

    boolean autoAssignStackNetworkId();

    ItemType<T> getItemType();

    void setItemType(ItemType<T> itemType);
}
