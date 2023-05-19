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
public interface ItemUnlitRedstoneTorch extends ItemStack {
    ItemType<ItemUnlitRedstoneTorch> TYPE = ItemTypeBuilder
            .builder(ItemUnlitRedstoneTorch.class)
            .vanillaItem(VanillaItemId.UNLIT_REDSTONE_TORCH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
