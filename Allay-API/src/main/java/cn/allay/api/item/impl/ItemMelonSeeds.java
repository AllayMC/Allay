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
public interface ItemMelonSeeds extends ItemStack {
    ItemType<ItemMelonSeeds> TYPE = ItemTypeBuilder
            .builder(ItemMelonSeeds.class)
            .vanillaItem(VanillaItemId.MELON_SEEDS, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
