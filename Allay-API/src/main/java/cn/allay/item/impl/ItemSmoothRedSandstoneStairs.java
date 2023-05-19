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
public interface ItemSmoothRedSandstoneStairs extends ItemStack {
    ItemType<ItemSmoothRedSandstoneStairs> TYPE = ItemTypeBuilder
            .builder(ItemSmoothRedSandstoneStairs.class)
            .vanillaItem(VanillaItemId.SMOOTH_RED_SANDSTONE_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
