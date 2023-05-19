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
public interface ItemPolishedBlackstoneBrickWall extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickWall> TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickWall.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_WALL, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
