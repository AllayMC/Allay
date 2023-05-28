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
public interface ItemCrackedPolishedBlackstoneBricks extends ItemStack {
    ItemType<ItemCrackedPolishedBlackstoneBricks> TYPE = ItemTypeBuilder
            .builder(ItemCrackedPolishedBlackstoneBricks.class)
            .vanillaItem(VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
