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
public interface ItemLog2 extends ItemStack {
    ItemType<ItemLog2> TYPE = ItemTypeBuilder
            .builder(ItemLog2.class)
            .vanillaItem(VanillaItemId.LOG2, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
