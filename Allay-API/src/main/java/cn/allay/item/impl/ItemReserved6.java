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
public interface ItemReserved6 extends ItemStack {
    ItemType<ItemReserved6> TYPE = ItemTypeBuilder
            .builder(ItemReserved6.class)
            .vanillaItem(VanillaItemId.RESERVED6, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
