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
public interface ItemExposedDoubleCutCopperSlab extends ItemStack {
    ItemType<ItemExposedDoubleCutCopperSlab> TYPE = ItemTypeBuilder
            .builder(ItemExposedDoubleCutCopperSlab.class)
            .vanillaItem(VanillaItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
