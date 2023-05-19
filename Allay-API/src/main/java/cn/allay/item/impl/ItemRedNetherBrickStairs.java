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
public interface ItemRedNetherBrickStairs extends ItemStack {
    ItemType<ItemRedNetherBrickStairs> TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStairs.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK_STAIRS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
