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
public interface ItemMangrovePropagule extends ItemStack {
    ItemType<ItemMangrovePropagule> TYPE = ItemTypeBuilder
            .builder(ItemMangrovePropagule.class)
            .vanillaItem(VanillaItemId.MANGROVE_PROPAGULE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
