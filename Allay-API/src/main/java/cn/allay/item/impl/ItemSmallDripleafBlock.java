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
public interface ItemSmallDripleafBlock extends ItemStack {
    ItemType<ItemSmallDripleafBlock> TYPE = ItemTypeBuilder
            .builder(ItemSmallDripleafBlock.class)
            .vanillaItem(VanillaItemId.SMALL_DRIPLEAF_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
