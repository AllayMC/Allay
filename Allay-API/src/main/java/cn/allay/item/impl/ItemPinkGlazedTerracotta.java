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
public interface ItemPinkGlazedTerracotta extends ItemStack {
    ItemType<ItemPinkGlazedTerracotta> TYPE = ItemTypeBuilder
            .builder(ItemPinkGlazedTerracotta.class)
            .vanillaItem(VanillaItemId.PINK_GLAZED_TERRACOTTA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
