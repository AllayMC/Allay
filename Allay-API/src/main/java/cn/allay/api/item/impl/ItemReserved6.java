package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
