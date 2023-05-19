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
public interface ItemDarkOakStairs extends ItemStack {
    ItemType<ItemDarkOakStairs> TYPE = ItemTypeBuilder
            .builder(ItemDarkOakStairs.class)
            .vanillaItem(VanillaItemId.DARK_OAK_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
