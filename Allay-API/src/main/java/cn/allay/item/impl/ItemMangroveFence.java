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
public interface ItemMangroveFence extends ItemStack {
    ItemType<ItemMangroveFence> TYPE = ItemTypeBuilder
            .builder(ItemMangroveFence.class)
            .vanillaItem(VanillaItemId.MANGROVE_FENCE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
