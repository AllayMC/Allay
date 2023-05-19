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
public interface ItemPlanks extends ItemStack {
    ItemType<ItemPlanks> TYPE = ItemTypeBuilder
            .builder(ItemPlanks.class)
            .vanillaItem(VanillaItemId.PLANKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
