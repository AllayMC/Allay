package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

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
