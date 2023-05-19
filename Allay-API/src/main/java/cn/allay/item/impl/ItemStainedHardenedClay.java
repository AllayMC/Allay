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
public interface ItemStainedHardenedClay extends ItemStack {
    ItemType<ItemStainedHardenedClay> TYPE = ItemTypeBuilder
            .builder(ItemStainedHardenedClay.class)
            .vanillaItem(VanillaItemId.STAINED_HARDENED_CLAY, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
