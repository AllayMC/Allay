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
public interface ItemCookedSalmon extends ItemStack {
    ItemType<ItemCookedSalmon> TYPE = ItemTypeBuilder
            .builder(ItemCookedSalmon.class)
            .vanillaItem(VanillaItemId.COOKED_SALMON, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
