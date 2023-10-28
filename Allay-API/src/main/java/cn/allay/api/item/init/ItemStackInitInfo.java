package cn.allay.api.item.init;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemStackInitInfo<T extends ItemStack> extends ComponentInitInfo {

    int count();

    int meta();

    NbtMap extraTag();

    @Range(from = 0, to = Integer.MAX_VALUE) int stackNetworkId();

    boolean autoAssignStackNetworkId();

    ItemType<T> getItemType();

    void setItemType(ItemType<T> itemType);
}
