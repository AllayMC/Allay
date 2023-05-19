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
public interface ItemStructureBlock extends ItemStack {
    ItemType<ItemStructureBlock> TYPE = ItemTypeBuilder
            .builder(ItemStructureBlock.class)
            .vanillaItem(VanillaItemId.STRUCTURE_BLOCK, true)
            .addBasicComponents()
            .build().register(ItemTypeRegistry.getRegistry());
}
