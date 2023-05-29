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
public interface ItemUnlitRedstoneTorch extends ItemStack {
    ItemType<ItemUnlitRedstoneTorch> TYPE = ItemTypeBuilder
            .builder(ItemUnlitRedstoneTorch.class)
            .vanillaItem(VanillaItemId.UNLIT_REDSTONE_TORCH, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
