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
public interface ItemStoneBlockSlab4 extends ItemStack {
    ItemType<ItemStoneBlockSlab4> TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlab4.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB4, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
