package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopper extends ItemStack {
    ItemType<ItemWaxedExposedCopper> TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopper.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
