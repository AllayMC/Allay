package cn.allay.api.item.impl;

import cn.allay.api.item.ItemStack;
import cn.allay.api.item.data.VanillaItemId;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLog2 extends ItemStack {
    ItemType<ItemLog2> TYPE = ItemTypeBuilder
            .builder(ItemLog2.class)
            .vanillaItem(VanillaItemId.LOG2, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
