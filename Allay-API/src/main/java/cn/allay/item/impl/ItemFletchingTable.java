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
public interface ItemFletchingTable extends ItemStack {
    ItemType<ItemFletchingTable> TYPE = ItemTypeBuilder
            .builder(ItemFletchingTable.class)
            .vanillaItem(VanillaItemId.FLETCHING_TABLE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
