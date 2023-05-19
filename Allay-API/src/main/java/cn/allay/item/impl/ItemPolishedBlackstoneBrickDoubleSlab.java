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
public interface ItemPolishedBlackstoneBrickDoubleSlab extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickDoubleSlab> TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickDoubleSlab.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
