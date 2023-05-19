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
public interface ItemPolishedBlackstoneBrickSlab extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickSlab> TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickSlab.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
