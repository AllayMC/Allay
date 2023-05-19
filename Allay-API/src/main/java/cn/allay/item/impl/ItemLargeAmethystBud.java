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
public interface ItemLargeAmethystBud extends ItemStack {
    ItemType<ItemLargeAmethystBud> TYPE = ItemTypeBuilder
            .builder(ItemLargeAmethystBud.class)
            .vanillaItem(VanillaItemId.LARGE_AMETHYST_BUD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
