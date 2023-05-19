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
public interface ItemSmallAmethystBud extends ItemStack {
    ItemType<ItemSmallAmethystBud> TYPE = ItemTypeBuilder
            .builder(ItemSmallAmethystBud.class)
            .vanillaItem(VanillaItemId.SMALL_AMETHYST_BUD, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
