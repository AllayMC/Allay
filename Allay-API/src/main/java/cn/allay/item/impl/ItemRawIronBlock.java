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
public interface ItemRawIronBlock extends ItemStack {
    ItemType<ItemRawIronBlock> TYPE = ItemTypeBuilder
            .builder(ItemRawIronBlock.class)
            .vanillaItem(VanillaItemId.RAW_IRON_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
