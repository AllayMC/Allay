package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
