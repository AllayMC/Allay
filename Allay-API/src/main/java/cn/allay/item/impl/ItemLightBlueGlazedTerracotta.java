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
public interface ItemLightBlueGlazedTerracotta extends ItemStack {
    ItemType<ItemLightBlueGlazedTerracotta> TYPE = ItemTypeBuilder
            .builder(ItemLightBlueGlazedTerracotta.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_GLAZED_TERRACOTTA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
