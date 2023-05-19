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
public interface ItemGrayGlazedTerracotta extends ItemStack {
    ItemType<ItemGrayGlazedTerracotta> TYPE = ItemTypeBuilder
            .builder(ItemGrayGlazedTerracotta.class)
            .vanillaItem(VanillaItemId.GRAY_GLAZED_TERRACOTTA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
