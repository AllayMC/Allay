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
public interface ItemLightBlueGlazedTerracotta extends ItemStack {
    ItemType<ItemLightBlueGlazedTerracotta> TYPE = ItemTypeBuilder
            .builder(ItemLightBlueGlazedTerracotta.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_GLAZED_TERRACOTTA, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
