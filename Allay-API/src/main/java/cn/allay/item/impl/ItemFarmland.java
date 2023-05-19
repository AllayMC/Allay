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
public interface ItemFarmland extends ItemStack {
    ItemType<ItemFarmland> TYPE = ItemTypeBuilder
            .builder(ItemFarmland.class)
            .vanillaItem(VanillaItemId.FARMLAND, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
