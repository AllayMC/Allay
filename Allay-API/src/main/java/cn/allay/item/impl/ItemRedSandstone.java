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
public interface ItemRedSandstone extends ItemStack {
    ItemType<ItemRedSandstone> TYPE = ItemTypeBuilder
            .builder(ItemRedSandstone.class)
            .vanillaItem(VanillaItemId.RED_SANDSTONE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
