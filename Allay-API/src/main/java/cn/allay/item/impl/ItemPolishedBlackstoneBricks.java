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
public interface ItemPolishedBlackstoneBricks extends ItemStack {
    ItemType<ItemPolishedBlackstoneBricks> TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBricks.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICKS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
