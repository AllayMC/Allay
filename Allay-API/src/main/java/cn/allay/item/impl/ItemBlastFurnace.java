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
public interface ItemBlastFurnace extends ItemStack {
    ItemType<ItemBlastFurnace> TYPE = ItemTypeBuilder
            .builder(ItemBlastFurnace.class)
            .vanillaItem(VanillaItemId.BLAST_FURNACE, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
