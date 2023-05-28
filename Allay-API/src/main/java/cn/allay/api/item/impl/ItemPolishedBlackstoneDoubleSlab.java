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
public interface ItemPolishedBlackstoneDoubleSlab extends ItemStack {
    ItemType<ItemPolishedBlackstoneDoubleSlab> TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneDoubleSlab.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
