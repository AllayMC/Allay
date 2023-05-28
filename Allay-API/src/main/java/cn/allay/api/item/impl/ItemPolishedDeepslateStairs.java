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
public interface ItemPolishedDeepslateStairs extends ItemStack {
    ItemType<ItemPolishedDeepslateStairs> TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateStairs.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
