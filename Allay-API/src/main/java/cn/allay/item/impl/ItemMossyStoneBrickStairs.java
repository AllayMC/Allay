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
public interface ItemMossyStoneBrickStairs extends ItemStack {
    ItemType<ItemMossyStoneBrickStairs> TYPE = ItemTypeBuilder
            .builder(ItemMossyStoneBrickStairs.class)
            .vanillaItem(VanillaItemId.MOSSY_STONE_BRICK_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
