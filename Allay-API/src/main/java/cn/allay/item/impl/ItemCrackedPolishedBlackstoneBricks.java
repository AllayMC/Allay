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
public interface ItemCrackedPolishedBlackstoneBricks extends ItemStack {
    ItemType<ItemCrackedPolishedBlackstoneBricks> TYPE = ItemTypeBuilder
            .builder(ItemCrackedPolishedBlackstoneBricks.class)
            .vanillaItem(VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
