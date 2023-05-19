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
public interface ItemHardStainedGlass extends ItemStack {
    ItemType<ItemHardStainedGlass> TYPE = ItemTypeBuilder
            .builder(ItemHardStainedGlass.class)
            .vanillaItem(VanillaItemId.HARD_STAINED_GLASS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
