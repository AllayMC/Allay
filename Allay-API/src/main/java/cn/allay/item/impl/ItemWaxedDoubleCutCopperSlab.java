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
public interface ItemWaxedDoubleCutCopperSlab extends ItemStack {
    ItemType<ItemWaxedDoubleCutCopperSlab> TYPE = ItemTypeBuilder
            .builder(ItemWaxedDoubleCutCopperSlab.class)
            .vanillaItem(VanillaItemId.WAXED_DOUBLE_CUT_COPPER_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
