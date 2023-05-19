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
public interface ItemHardGlass extends ItemStack {
    ItemType<ItemHardGlass> TYPE = ItemTypeBuilder
            .builder(ItemHardGlass.class)
            .vanillaItem(VanillaItemId.HARD_GLASS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
