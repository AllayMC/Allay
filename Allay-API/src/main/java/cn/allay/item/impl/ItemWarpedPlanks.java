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
public interface ItemWarpedPlanks extends ItemStack {
    ItemType<ItemWarpedPlanks> TYPE = ItemTypeBuilder
            .builder(ItemWarpedPlanks.class)
            .vanillaItem(VanillaItemId.WARPED_PLANKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
