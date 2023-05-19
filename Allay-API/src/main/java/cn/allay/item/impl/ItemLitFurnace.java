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
public interface ItemLitFurnace extends ItemStack {
    ItemType<ItemLitFurnace> TYPE = ItemTypeBuilder
            .builder(ItemLitFurnace.class)
            .vanillaItem(VanillaItemId.LIT_FURNACE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
