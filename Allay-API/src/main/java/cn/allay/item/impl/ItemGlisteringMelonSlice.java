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
public interface ItemGlisteringMelonSlice extends ItemStack {
    ItemType<ItemGlisteringMelonSlice> TYPE = ItemTypeBuilder
            .builder(ItemGlisteringMelonSlice.class)
            .vanillaItem(VanillaItemId.GLISTERING_MELON_SLICE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
