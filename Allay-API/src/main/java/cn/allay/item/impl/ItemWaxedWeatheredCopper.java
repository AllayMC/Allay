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
public interface ItemWaxedWeatheredCopper extends ItemStack {
    ItemType<ItemWaxedWeatheredCopper> TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopper.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
