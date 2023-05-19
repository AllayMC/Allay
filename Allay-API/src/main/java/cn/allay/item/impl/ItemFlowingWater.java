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
public interface ItemFlowingWater extends ItemStack {
    ItemType<ItemFlowingWater> TYPE = ItemTypeBuilder
            .builder(ItemFlowingWater.class)
            .vanillaItem(VanillaItemId.FLOWING_WATER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
