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
public interface ItemRedstoneTorch extends ItemStack {
    ItemType<ItemRedstoneTorch> TYPE = ItemTypeBuilder
            .builder(ItemRedstoneTorch.class)
            .vanillaItem(VanillaItemId.REDSTONE_TORCH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
