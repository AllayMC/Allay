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
public interface ItemMossyStoneBrickStairs extends ItemStack {
    ItemType<ItemMossyStoneBrickStairs> TYPE = ItemTypeBuilder
            .builder(ItemMossyStoneBrickStairs.class)
            .vanillaItem(VanillaItemId.MOSSY_STONE_BRICK_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
