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
public interface ItemNormalStoneStairs extends ItemStack {
    ItemType<ItemNormalStoneStairs> TYPE = ItemTypeBuilder
            .builder(ItemNormalStoneStairs.class)
            .vanillaItem(VanillaItemId.NORMAL_STONE_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
