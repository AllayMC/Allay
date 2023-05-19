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
public interface ItemRapidFertilizer extends ItemStack {
    ItemType<ItemRapidFertilizer> TYPE = ItemTypeBuilder
            .builder(ItemRapidFertilizer.class)
            .vanillaItem(VanillaItemId.RAPID_FERTILIZER, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
