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
public interface ItemChemistryTable extends ItemStack {
    ItemType<ItemChemistryTable> TYPE = ItemTypeBuilder
            .builder(ItemChemistryTable.class)
            .vanillaItem(VanillaItemId.CHEMISTRY_TABLE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
