package cn.allay.item.impl;

import cn.allay.item.ItemStack;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemType;
import cn.allay.item.type.ItemTypeBuilder;
import cn.allay.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveStairs extends ItemStack {
    ItemType<ItemMangroveStairs> TYPE = ItemTypeBuilder
            .builder(ItemMangroveStairs.class)
            .vanillaItem(VanillaItemId.MANGROVE_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
