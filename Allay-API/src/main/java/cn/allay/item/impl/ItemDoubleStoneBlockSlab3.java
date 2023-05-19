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
public interface ItemDoubleStoneBlockSlab3 extends ItemStack {
    ItemType<ItemDoubleStoneBlockSlab3> TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab3.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB3, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
