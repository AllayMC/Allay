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
public interface ItemStoneBlockSlab3 extends ItemStack {
    ItemType<ItemStoneBlockSlab3> TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlab3.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB3, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
