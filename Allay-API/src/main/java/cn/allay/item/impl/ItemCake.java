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
public interface ItemCake extends ItemStack {
    ItemType<ItemCake> TYPE = ItemTypeBuilder
            .builder(ItemCake.class)
            .vanillaItem(VanillaItemId.CAKE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
