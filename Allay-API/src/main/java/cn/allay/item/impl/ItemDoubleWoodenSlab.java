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
public interface ItemDoubleWoodenSlab extends ItemStack {
    ItemType<ItemDoubleWoodenSlab> TYPE = ItemTypeBuilder
            .builder(ItemDoubleWoodenSlab.class)
            .vanillaItem(VanillaItemId.DOUBLE_WOODEN_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
