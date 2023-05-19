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
public interface ItemNetherWartBlock extends ItemStack {
    ItemType<ItemNetherWartBlock> TYPE = ItemTypeBuilder
            .builder(ItemNetherWartBlock.class)
            .vanillaItem(VanillaItemId.NETHER_WART_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
