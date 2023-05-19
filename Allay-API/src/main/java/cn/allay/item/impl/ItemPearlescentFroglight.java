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
public interface ItemPearlescentFroglight extends ItemStack {
    ItemType<ItemPearlescentFroglight> TYPE = ItemTypeBuilder
            .builder(ItemPearlescentFroglight.class)
            .vanillaItem(VanillaItemId.PEARLESCENT_FROGLIGHT, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
