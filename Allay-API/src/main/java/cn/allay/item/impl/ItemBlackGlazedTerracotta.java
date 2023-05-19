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
public interface ItemBlackGlazedTerracotta extends ItemStack {
    ItemType<ItemBlackGlazedTerracotta> TYPE = ItemTypeBuilder
            .builder(ItemBlackGlazedTerracotta.class)
            .vanillaItem(VanillaItemId.BLACK_GLAZED_TERRACOTTA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
