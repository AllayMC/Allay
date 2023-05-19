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
public interface ItemMangroveFenceGate extends ItemStack {
    ItemType<ItemMangroveFenceGate> TYPE = ItemTypeBuilder
            .builder(ItemMangroveFenceGate.class)
            .vanillaItem(VanillaItemId.MANGROVE_FENCE_GATE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
