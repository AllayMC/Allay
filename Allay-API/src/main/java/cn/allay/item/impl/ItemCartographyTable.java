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
public interface ItemCartographyTable extends ItemStack {
    ItemType<ItemCartographyTable> TYPE = ItemTypeBuilder
            .builder(ItemCartographyTable.class)
            .vanillaItem(VanillaItemId.CARTOGRAPHY_TABLE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
