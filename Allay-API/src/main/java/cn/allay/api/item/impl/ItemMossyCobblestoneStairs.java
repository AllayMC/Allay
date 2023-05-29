package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyCobblestoneStairs extends ItemStack {
    ItemType<ItemMossyCobblestoneStairs> TYPE = ItemTypeBuilder
            .builder(ItemMossyCobblestoneStairs.class)
            .vanillaItem(VanillaItemId.MOSSY_COBBLESTONE_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
