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
public interface ItemFrostedIce extends ItemStack {
    ItemType<ItemFrostedIce> TYPE = ItemTypeBuilder
            .builder(ItemFrostedIce.class)
            .vanillaItem(VanillaItemId.FROSTED_ICE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
