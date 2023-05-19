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
public interface ItemDoubleStoneBlockSlab4 extends ItemStack {
    ItemType<ItemDoubleStoneBlockSlab4> TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab4.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB4, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
